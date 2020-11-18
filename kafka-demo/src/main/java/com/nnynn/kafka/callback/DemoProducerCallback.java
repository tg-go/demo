package com.nnynn.kafka.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class DemoProducerCallback implements Callback {
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            e.printStackTrace();
        }
        System.out.println(recordMetadata.offset() + "," + recordMetadata.partition() + "," + recordMetadata.topic());
    }
}
