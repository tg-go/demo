package com.nnynn.mbean.notify;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * @author bo.luo
 * @date 2020/12/15 16:22
 */
public class Home extends NotificationBroadcasterSupport implements HomeMBean {

    private int seq = 0;

    public void hi() {
        Notification notification = new Notification(
                "home,hi",//这个notify的名称
                this,// 由谁发送
                ++seq, // 一系列通知中的序列号,可以设置任意数值
                System.currentTimeMillis(),// 发出时间
                "hi" // 发送消息的消息文本
        );

        sendNotification(notification);
    }
}
