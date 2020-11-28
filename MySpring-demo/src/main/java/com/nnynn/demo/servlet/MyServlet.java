package com.nnynn.demo.servlet;

import com.nnynn.demo.annotation.MyAutowired;
import com.nnynn.demo.annotation.MyController;
import com.nnynn.demo.annotation.MyRequestMapping;
import com.nnynn.demo.annotation.MyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 梳理一下整体思路
 * （1）读取properties文件
 * （2）根据scanPackage扫描对应的class文件，并进行加载
 * （3）对于单个class文件进行注解解析，放入到Mapping
 * （4）进行依赖注入
 */
public class MyServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    private Map<String, Object> mapping = new HashMap<String, Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 ERROR. " + e.getStackTrace());
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getRequestURI();
        logger.info("Http请求url:{}", url);

        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        logger.info("替换后的url:{}", url);

        if (!this.mapping.containsKey(url)) {
            response.getWriter().write("404 Not Found");
            return;
        }
        Method method = (Method) this.mapping.get(url);
        Map<String, String[]> params = request.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()), new Object[]{request, response, params.get("name")[0]});

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        try {

            // 根据init-param读取属性
            Properties configProp = new Properties();
            inputStream = this.getClass().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            configProp.load(inputStream);

            String scanPackage = configProp.getProperty("scanPackage");
            // 扫描包
            doScanner(scanPackage);

            for (String className : mapping.keySet()) {
                logger.info("className={}", className);
                if (!className.contains(".")) {
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    mapping.put(className, clazz.newInstance());
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                        MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                            continue;
                        }
                        MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);
                        String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                        mapping.put(url, method);
                        logger.info("Mapped {},{}", url, method);
                    }
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    MyService service = clazz.getAnnotation(MyService.class);
                    String beanName = service.value();
                    if ("".equals(beanName)) {
                        beanName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    mapping.put(beanName, instance);
                    // d对应的接口也需要设置相应的实例
                    for (Class<?> i : clazz.getInterfaces()) {
                        mapping.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }

            // 通过反射进行依赖注入
            for (Object object : mapping.values()) {
                if (object == null) {
                    continue;
                }
                Class clazz = object.getClass();
                if (clazz.isAnnotationPresent(MyController.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(MyAutowired.class)) {
                            continue;
                        }
                        MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                        String beanName = autowired.value();
                        if ("".equals(beanName)) {
                            beanName = field.getType().getName();
                        }
                        field.setAccessible(true);
                        field.set(mapping.get(clazz.getName()), mapping.get(beanName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描配置的class对象
     *
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File clasDir = new File(url.getFile());
        for (File file : clasDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String clazzName = (scanPackage + "." + file.getName().replace(".class", ""));
                mapping.put(clazzName, null);
            }
        }
    }


}
