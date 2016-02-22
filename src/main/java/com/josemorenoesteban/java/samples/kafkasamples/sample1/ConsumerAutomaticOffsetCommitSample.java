package com.josemorenoesteban.java.samples.kafkasamples.sample1;

import static com.josemorenoesteban.java.samples.kafkasamples.sample1.Constants.*;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerAutomaticOffsetCommitSample {
    public static void main(String... args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers",       KAFKA_SERVER);
        props.put("group.id",                "test");
        props.put("enable.auto.commit",      true);
        props.put("auto.commit.interval.ms", 1_000);
        props.put("session.timeout.ms",      30_000);
        props.put("key.deserializer",        "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",      "org.apache.kafka.common.serialization.StringDeserializer");
        
        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC));
        while (true) {
            consumer.poll(100).forEach((record) -> {
                System.out.printf("Consumiing... offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
            });
        }
    }
}
