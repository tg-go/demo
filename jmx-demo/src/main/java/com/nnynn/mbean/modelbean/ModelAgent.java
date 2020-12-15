package com.nnynn.mbean.modelbean;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;
import javax.management.modelmbean.RequiredModelMBean;
import java.lang.management.ManagementFactory;

/**
 * @author bo.luo
 * @date 2020/12/15 16:50
 */
public class ModelAgent {

    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {

        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        ObjectName helloName = new ObjectName("MyMBean:name=HelloWorld");
        //Hello hello = new Hello();
        RequiredModelMBean hello = ModelMBeanUtils.createModelerMBean();
        server.registerMBean(hello, helloName);

        ObjectName adapterName = new ObjectName("MyMBean:name=htmladapter,port=8082");
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();
        server.registerMBean(adapter, adapterName);
        adapter.start();
    }
}
