# kafka-sample

Apache Kafka samples. Following [Adictos al Trabajo - Primeros pasos con Apache Kafka](http://www.adictosaltrabajo.com/tutoriales/kafka-logs/)


# Kafka 

Start Kafka container

    docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST="172.17.0.1" --env ADVERTISED_PORT=9092 spotify/kafka


    docker run -d \
        -p 2181:2181 -p 9092:9092 \
        --env ADVERTISED_HOST=192.168.1.37 \
        --env ADVERTISED_PORT=9092 \
        flozano/kafka:0-9.0.0

OJO!! La imagen de Spotify es la 0.8.2.0 y el API que estoy utilizando yo es el 0.9.0.0
que requiere esa versión. Cambiado a una imagen de [flozano](https://hub.docker.com/r/flozano/)
y listo


# Start a proxy

    docker run -p 2181:2181 -p 9092:9092 \
        --env ADVERTISED_HOST=192.168.1.37 \
        --env ADVERTISED_PORT=9092 \
        --env CONSUMER_THREADS=1 \
        --env TOPICS=my-topic \
        --env ZK_CONNECT=kafka7zookeeper:2181/root/path \
        --env GROUP_ID=mymirror \
        spotify/kafkaproxy


172.17.0.1