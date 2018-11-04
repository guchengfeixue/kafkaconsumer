package com.hyd.kafkaclient.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class Producer
{
    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private Logger logger = LoggerFactory.getLogger(Producer.class);

    public Producer(String topic)
    {
        Properties props = getConfig();
        this.topic = topic;
        producer = new KafkaProducer<>(props);

    }

    private Properties getConfig()
    {
        PropertyConfigurator.configure("src/main/resources/conf/log.conf");
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "DemoProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

    public void start()
    {
        int messageNo = 1;
        while(true)
        {
            String messageStr = "Message_" + messageNo + " this is a test!!";
            try
            {
                producer.send(new ProducerRecord<>(topic, messageNo, messageStr)).get();
                System.out.println("Sent message: (" + messageNo + ", " + messageStr + ")");
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
            ++messageNo;
        }
    }

    public static void main(String args[])
    {
        new Producer(KafkaProperties.TOPIC).start();
    }
}
