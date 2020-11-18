package com.nnynn.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

public class HandleRebalance implements ConsumerRebalanceListener {


    //在失去分区控制之前，进行的业务处理
    public void onPartitionsRevoked(Collection<TopicPartition> collection) {
        System.out.println("lost partition.offset:now offset");

    }

    // 在获取新的分区的业务处理
    public void onPartitionsAssigned(Collection<TopicPartition> collection) {

    }
}
