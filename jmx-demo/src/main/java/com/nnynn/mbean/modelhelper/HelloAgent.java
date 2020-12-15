package com.nnynn.mbean.modelhelper;


import com.sun.jdmk.comm.HtmlAdaptorServer;
import org.apache.commons.modeler.ManagedBean;
import org.apache.commons.modeler.Registry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.modelmbean.ModelMBean;
import java.io.InputStream;

/**
 * @author bo.luo
 * @date 2020/12/15 17:12
 */
public class HelloAgent {

    public static void main(String[] args) throws Exception {
        Registry registry = Registry.getRegistry(null,null);
        InputStream stream = HelloAgent.class.getResourceAsStream("mbeans-descriptors.xml");
        registry.loadMetadata(stream);
        MBeanServer server = registry.getMBeanServer();

        ManagedBean managedBean = registry.findManagedBean("Hello");
        ObjectName helloName = new ObjectName(managedBean.getDomain()+":name=HelloWorld");
        ModelMBean hello = managedBean.createMBean(new Hello());
        server.registerMBean(hello,helloName);

        ObjectName adapterName = new ObjectName(managedBean.getDomain()+":name = htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter,adapterName);
        adapter.start();

    }
}
