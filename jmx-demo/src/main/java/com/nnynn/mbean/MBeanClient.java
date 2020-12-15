package com.nnynn.mbean;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;

/**
 * @author bo.luo
 * @date 2020/12/15 16:08
 */
public class MBeanClient {

    public static void main(String[] args) throws IOException, MalformedObjectNameException, AttributeNotFoundException, MBeanException, ReflectionException, InstanceNotFoundException, InvalidAttributeValueException {
        String domainName = "MyBean";
        int rmiPort = 1099;
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + domainName);
        JMXConnector jmxc = JMXConnectorFactory.connect(url);
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        // print domain
        System.out.println("Domains------------");
        String[] domains = mbsc.getDomains();
        System.out.println(Arrays.toString(domains));

        // MBean count
        System.out.println("MBean count=" + mbsc.getMBeanCount());

        // process attribute
        ObjectName mBeanName = new ObjectName(domainName + ":name=HelloWorld");
        mbsc.setAttribute(mBeanName,new Attribute("Name","luobo"));
        System.out.println("Name="+mbsc.getAttribute(mBeanName,"Name"));

        // 执行Hello中的print方法
        // via proxy
        HelloMBean proxy = MBeanServerInvocationHandler.newProxyInstance(mbsc,mBeanName,HelloMBean.class,false);
        proxy.printHello();
        proxy.printHello("jizhi boy");
        // via rmi
        mbsc.invoke(mBeanName,"printHello",null,null);
        mbsc.invoke(mBeanName,"printHello",new String[]{"jizhi girl"},new String[]{String.class.getName()});

    }
}
