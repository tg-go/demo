package com.nnynn.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.record.InvalidRecordException;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;

public class BananaPartitioner implements Partitioner {


    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int numPartitions = partitionInfos.size();

        if ((keyBytes == null) || (!(key instanceof String))) {
            throw new InvalidRecordException("error");
        }
        if (((String) key).equals("Banana")) {
            return numPartitions;
        }
        return Math.abs(Utils.murmur2(keyBytes)) % (numPartitions - 1);
    }

    /**
     * 关闭信息
     */
    public void close() {

    }

    /**
     * 配置信息
     *
     * @param map
     */
    public void configure(Map<String, ?> map) {

    }
}
