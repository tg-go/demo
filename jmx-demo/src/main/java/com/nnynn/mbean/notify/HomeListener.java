package com.nnynn.mbean.notify;

import com.nnynn.mbean.Hello;

import javax.management.Notification;
import javax.management.NotificationListener;

/**
 * @author bo.luo
 * @date 2020/12/15 16:25
 */
public class HomeListener implements NotificationListener {
    public void handleNotification(Notification notification, Object handback) {
        System.out.println("start----");
        System.out.println("\ttype=" + notification.getType());
        System.out.println("\tsource=" + notification.getSource());
        System.out.println("\tseq=" + notification.getSequenceNumber());
        System.out.println("\tsend time=" + notification.getTimeStamp());
        System.out.println("\tmessage=" + notification.getMessage());
        System.out.println("end------");

        if (handback != null) {
            if(handback instanceof Hello){
                Hello hello = (Hello) handback;
                hello.printHello(notification.getMessage());
            }
        }
    }
}
