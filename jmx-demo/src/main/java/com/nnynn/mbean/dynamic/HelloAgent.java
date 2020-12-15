package com.nnynn.mbean.dynamic;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * @author bo.luo
 * @date 2020/12/15 17:16
 */
public class HelloAgent {

    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("MyMBean:name=helloDynamic");
        HelloDynamic hello = new HelloDynamic();
        server.registerMBean(hello,helloName);

        ObjectName adapterName = new ObjectName("MyMBean:name=htmladapter");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter,adapterName);
        adapter.start();
    }
}
