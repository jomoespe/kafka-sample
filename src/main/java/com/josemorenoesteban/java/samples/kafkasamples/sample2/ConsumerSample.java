package com.josemorenoesteban.java.samples.kafkasamples.sample2;

import static com.josemorenoesteban.java.samples.kafkasamples.sample2.Constants.*;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerSample {
    public static void main(String... args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers",       KAFKA_SERVER);
        props.put("group.id",                "test");
        props.put("enable.auto.commit",      true);
        props.put("auto.commit.interval.ms", 1_000);
        props.put("session.timeout.ms",      30_000);
        props.put("key.deserializer",        "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put("value.deserializer",      "org.apache.kafka.common.serialization.StringDeserializer");
        
        try (Consumer<Integer, String> consumer = new KafkaConsumer<>(props) ) {
            consumer.subscribe(Arrays.asList(TOPIC));
            while (true) {
                ConsumerRecords<Integer, String> records = consumer.poll(100);
                records.forEach((record) -> {
                    System.out.printf("Message: key=%s, value=%s\n", 
                                      record.key(), record.value());
                });
            }
        }
    }
}
