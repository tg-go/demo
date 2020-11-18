package com.nnynn.kafka.serializer;

import com.nnynn.kafka.model.Customer;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 使用这种方式的序列化会有不兼容的问题（新旧消息不兼通）
 */
public class CustomerSerializer implements Serializer<Customer> {
    /**
     * 配置
     *
     * @param map
     * @param b
     */
    public void configure(Map<String, ?> map, boolean b) {
        //不做任何配置
    }

    /**
     * Customer对象被序列化过程
     *
     * @param topic
     * @param data
     * @return
     */
    public byte[] serialize(String topic, Customer data) {
        try {
            byte[] serializedName;
            int stringSize;
            if (data == null) {
                return null;
            } else {
                if (data.getCustomerName() != null) {
                    serializedName = data.getCustomerName().getBytes("UTF-8");
                    stringSize = serializedName.length;
                } else {
                    serializedName = new byte[0];
                    stringSize = 0;
                }
                ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + stringSize);
                buffer.putInt(data.getCustomerID());
                buffer.putInt(stringSize);
                buffer.put(serializedName);

                return buffer.array();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException("Error");

        }
    }

    public void close() {
        //不需要关闭任何东西
    }
}
