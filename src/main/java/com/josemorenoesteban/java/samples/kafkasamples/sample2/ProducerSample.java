package com.josemorenoesteban.java.samples.kafkasamples.sample2;

import static com.josemorenoesteban.java.samples.kafkasamples.sample2.Constants.*;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerSample {
    
    final static String MESSAGE = "{\"id\": %s, \"message\": \"%s\"}";
    
    public static void main(String... args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_SERVER);
        props.put("acks",              "all");
        props.put("retries",           5);
        props.put("batch.size",        16_384);
        props.put("linger.ms",         1);
        props.put("buffer.memory",     33_554_432);
        props.put("key.serializer",    "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put("value.serializer",  "org.apache.kafka.common.serialization.StringSerializer");

        final Integer ID_1 = 1;
        final Integer ID_2 = 2;
        
        try ( Producer<Integer, String> producer = new KafkaProducer<>(props) ) {
            producer.send(new ProducerRecord<>(TOPIC, ID_1, String.format(MESSAGE, ID_1, "mensaje 1")));
            producer.send(new ProducerRecord<>(TOPIC, ID_1, String.format(MESSAGE, ID_1, "mensaje 2")));
            producer.send(new ProducerRecord<>(TOPIC, ID_2, String.format(MESSAGE, ID_2, "mensaje 1")));
            producer.send(new ProducerRecord<>(TOPIC, ID_1, String.format(MESSAGE, ID_1, "mensaje 3")));
            producer.send(new ProducerRecord<>(TOPIC, ID_1, String.format(MESSAGE, ID_1, "mensaje 4")));
            producer.send(new ProducerRecord<>(TOPIC, ID_2, String.format(MESSAGE, ID_2, "mensaje 2")));
            producer.send(new ProducerRecord<>(TOPIC, ID_2, String.format(MESSAGE, ID_2, "mensaje 3")));
            producer.send(new ProducerRecord<>(TOPIC, ID_1, String.format(MESSAGE, ID_1, "mensaje 5")));
            producer.send(new ProducerRecord<>(TOPIC, ID_2, String.format(MESSAGE, ID_2, "mensaje 4")));
        }
    }
}
