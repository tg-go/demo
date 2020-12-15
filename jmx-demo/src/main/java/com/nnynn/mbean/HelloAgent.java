package com.nnynn.mbean;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author bo.luo
 * @date 2020/12/15 10:37
 */
public class HelloAgent {


    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        // 这种方式已经在JConsole中不能使用了
        //MBeanServer server = MBeanServerFactory.createMBeanServer();
        // 创建MBeanServer，来获取MBean
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

        String domainName = "MyBean";
        ObjectName helloName = new ObjectName(domainName + ":name=HelloWorld");
        mbs.registerMBean(new Hello(), helloName);

        ObjectName adapterName = new ObjectName(domainName + ":name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        adapter.start();
        mbs.registerMBean(adapter, adapterName);

        int rmiPort = 1099;
        Registry registry = LocateRegistry.createRegistry(rmiPort);

        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:" + rmiPort + "/" + domainName);

        JMXConnectorServer jmxConnector = JMXConnectorServerFactory.newJMXConnectorServer(url,null,mbs);

        jmxConnector.start();
    }
}
