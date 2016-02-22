package com.josemorenoesteban.java.samples.kafkasamples.sample1;

import static com.josemorenoesteban.java.samples.kafkasamples.sample1.Constants.*;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerSample {
    public static void main(String... args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_SERVER);
        props.put("acks",              "all");
        props.put("retries",           5);
        props.put("batch.size",        16_384);
        props.put("linger.ms",         1);
        props.put("buffer.memory",     33_554_432);
        props.put("key.serializer",    "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer");
        
        try (Producer<String, String> producer = new KafkaProducer<>(props)) {
            for (int i = 0; i < 100; i++) {
                System.out.printf("lets send message... %d\n", i);
                producer.send(new ProducerRecord<>(TOPIC, Integer.toString(i), Integer.toString(i)));
            }
        }
    }
}
