package com.nnynn.kafka;

import com.nnynn.kafka.callback.DemoProducerCallback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Future;

public class KafkaDemo {

    private KafkaProducer<String,String> producer;

    @Before
    public void createProducer(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(properties);
    }

    /**
     * 同步发送消息，由后台线程发送
     * 有可能抛两种异常
     * （1）可重试
     * （2）无法重试，消息体太大等等，直接抛异常
     */
    @Test
    public void sendDemo(){
        ProducerRecord<String,String> record = new ProducerRecord<String, String>("CustomerCountry","Precision Products","France");
        try {
            Future<RecordMetadata> send = producer.send(record);
            System.out.println(send.get().offset());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void asyncSend() throws IOException {
        ProducerRecord<String,String> record =
                new ProducerRecord<String, String>("CustomerCountry","Precision Products","France");
        producer.send(record,new DemoProducerCallback());

        System.in.read();

    }

    @Test
    public void arvoDemo(){

    }
}
