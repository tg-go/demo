package com.nnynn.mbean.notify;

import com.nnynn.mbean.Hello;
import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @author bo.luo
 * @date 2020/12/15 16:29
 */
public class HelloAgent {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName("MyBean:name=HelloWorld");
        Hello hello = new Hello();
        server.registerMBean(hello, helloName);

        ObjectName adapterName = new ObjectName("MyBean:name=htmladapter,port=8082");
        HtmlAdaptorServer adaptorServer = new HtmlAdaptorServer();
        server.registerMBean(adaptorServer, adapterName);

        Home home = new Home();
        server.registerMBean(home, new ObjectName("MyBean:name=home"));
        home.addNotificationListener(new HomeListener(), null, hello);
        adaptorServer.start();
    }
}
