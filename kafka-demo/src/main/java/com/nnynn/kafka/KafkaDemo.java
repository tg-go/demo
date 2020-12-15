package com.nnynn.kafka;

import com.google.common.collect.Lists;
import com.nnynn.kafka.callback.DemoProducerCallback;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Future;

public class KafkaDemo {

    private static final Logger logger = LoggerFactory.getLogger(KafkaDemo.class);

    private KafkaProducer<String, String> producer;

    private KafkaConsumer<String, String> consumer;

    @Before
    public void createProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "42.192.221.34:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);

        Properties consumerProp = new Properties();
        consumerProp.put("bootstrap.servers", "42.192.221.34:9092");
        consumerProp.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProp.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 消费者比较生产者增加了groupId（消费组的概念）
        consumerProp.put("group.id", "CountryCounter");
        // 设置不自动提交offset
        consumerProp.put("auto.commit.offset", false);
        consumer = new KafkaConsumer<String, String>(consumerProp);
    }

    /**
     * 同步发送消息，由后台线程发送
     * 有可能抛两种异常
     * （1）可重试
     * （2）无法重试，消息体太大等等，直接抛异常
     */
    @Test
    public void sendDemo() {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>("CustomerCountry", "Precision Products", "France");
        try {
            Future<RecordMetadata> send = producer.send(record);
            //logger.info("{}",GlobalJsonUtils.toJsonString(send.get()));
            System.out.println(send.get().offset());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void asyncSend() throws IOException {
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>("CustomerCountry", "Precision Products", "France");
        producer.send(record, new DemoProducerCallback());

        System.in.read();

    }

    @Test
    public void arvoDemo() {

    }

    @Test
    public void consumerDemo() {
        // 1、订阅主题，支持正则表达式
        consumer.subscribe(Collections.singletonList("CustomerCountry"));
        //consumer.subscribe(Lists.newArrayList("test*"));
        //2、轮询消息
        try {
            while (true) {
                // 每次轮询会发送消费者的心跳检测以及自动commit offset
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord record : records) {
                    logger.info("topic={},partition={},offset={},customer={},country={}",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 通知群组领导者退出群组，并触发分区再均衡
            consumer.close();
        }

    }

    /**
     * 取消AutoCommit offset
     * commitSync
     */
    @Test
    public void consumerBySyncCommit() {
        consumer.subscribe(Lists.newArrayList("CustomerCountry"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord record : records) {
                    //DO BUSINESS
                    try {
                        // 等待broker响应，一直阻塞
                        consumer.commitSync();
                    } catch (Exception e) {
                        logger.error("error");
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }

    @Test
    public void consumerByAsyncCommit() {
        consumer.subscribe(Lists.newArrayList("CustomerCountry"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord record : records) {
                    //do business
                }
                // 异步提交offset
                // 这里还是会有可能出现重复消息
                // 可以在回调里比较本地的自增序列，来进行比对
                consumer.commitAsync(new OffsetCommitCallback() {
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> map, Exception e) {
                        if (e != null) {
                            logger.error("{}", e);
                        }
                    }
                });
            }
        } finally {
            try {
                // 在退出之前，确保能够提交成功
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    /**
     * 分批处理一个poll循环中的消息offset提交
     */
    @Test
    public void commitByBatch() {
        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();
        int count = 0;
        consumer.subscribe(Lists.<String>newArrayList("CustomerCountry"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord record : records) {
                    // do business
                    logger.info("topic={},partition={},offset={},customer={},country={}",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                    if (count % 100 == 0) {
                        consumer.commitSync(currentOffsets);
                    }
                    count++;
                }
            }
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    /**
     * 在消费者失去对一个分区的所有权时，进行业务处理（资源清理）
     */
    @Test
    public void partitionRevoked() {
        final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();
        int count = 0;
        consumer.subscribe(Lists.<String>newArrayList("CustomerCountry"), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("lost partition,offset=" + currentOffsets);
                consumer.commitSync(currentOffsets);
            }

            public void onPartitionsAssigned(Collection<TopicPartition> collection) {

            }
        });
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord record : records) {
                    // do business
                    logger.info("topic={},partition={},offset={},customer={},country={}",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1, "no metadata"));
                    if (count % 100 == 0) {
                        consumer.commitSync(currentOffsets);
                    }
                    count++;
                }
            }
        } finally {
            try {
                consumer.commitSync();
            } finally {
                consumer.close();
            }
        }
    }

    /**
     * 在分区再均衡之前，进行commit offset
     * 在分区再均衡之后，从指定的地方读取offset
     */
    @Test
    public void partitionAssign() {
        final Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<TopicPartition, OffsetAndMetadata>();
        int count = 0;
        consumer.subscribe(Lists.<String>newArrayList("CustomerCountry"), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                // commit to DB
            }

            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                for (TopicPartition partition : collection) {
                    long offset = 1;// read from db
                    consumer.seek(partition, offset);
                }
            }
        });

        // 这里调用一次poll的作用是，让当前消费者能够加入到消费者群组中，并重新获取分区
        // 这里会触发onPartitionAssigned方法，调用seek定位offset
        // 后续的poll会正确获取offset
        consumer.poll(0);
        for (TopicPartition partition : consumer.assignment()) {
            consumer.seek(partition, 1); // read offset from db
        }
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord record : records) {
                // do business
                // processRecord
                // storeOffsetInDb(topic,partition,offset)
            }
            //commitDBTransaction
        }

    }


    /**
     * 需要注意的是 后续新增的分区不会添加进来。所以需要周期性的调用partitionsFor方法进行更新
     * 或者重启应用
     */
    @Test
    public void assignPartition() {
        List<PartitionInfo> partitionInfos = consumer.partitionsFor("ConsumerCountry");
        List<TopicPartition> partitions = Lists.newArrayList();
        if (partitionInfos != null) {
            for (PartitionInfo partitionInfo : partitionInfos) {
                partitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
            }
            consumer.assign(partitions);
        }
    }
}
