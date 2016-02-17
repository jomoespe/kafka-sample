package com.josemorenoesteban.java.samples.kafkasamples.sample1;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer {
    private static final String KAFKA_SERVER = "localhost:9092";
    private static final String TOPIC        = "test";

    private final Producer<String, String> producer;
    
    KafkaProducer() {
        final Properties props = new Properties();
        props.put("metadata.broker.list", KAFKA_SERVER);
        props.put("serializer.class",     "kafka.serializer.StringEncoder");
        producer = new Producer<>(new ProducerConfig(props));
    }
    
    public void send(String topic, String message) {
        producer.send(new KeyedMessage<>(topic, message));
    }
    
    public static void main(String...args) {
        new KafkaProducer().send(TOPIC, "Esto es un mensaje");
    }
}
