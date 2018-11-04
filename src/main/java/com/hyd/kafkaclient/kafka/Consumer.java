package com.hyd.kafkaclient.kafka;

import com.hyd.kafkaclient.Application;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer
{
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;
//    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    public Consumer(String topic)
    {
        Properties props = getConfig();
        consumer = new KafkaConsumer<>(props);
        this.topic = topic;
    }

    private Properties getConfig()
    {
//        PropertyConfigurator.configure("src/main/resources/conf/log.conf");
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFKA_SERVER_URL + ":" + KafkaProperties.KAFKA_SERVER_PORT);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaProperties.CLIENT_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public void start()
    {
        consumer.subscribe(Collections.singletonList(this.topic));
        while(true)
        {
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord<Integer, String> record: records)
            {
                System.out.println("Received message: (" + record.key()+", " + record.value() + ") offset=" + record.offset() +" partition="+record.partition());
                onReceiveMsg(record);
            }
        }
    }

    private void onReceiveMsg(ConsumerRecord<Integer, String> record)
    {
        KafkaMsgPojo kafkaMsgPojo = convertRecordToPojo(record);
        KafkaDataManager.getInstance().addTopicMsg(topic, kafkaMsgPojo);
        Application.getInstance().onMsgUpdate();
    }

    private KafkaMsgPojo convertRecordToPojo(ConsumerRecord<Integer, String> record)
    {
        KafkaMsgPojo kafkaMsgPojo = new KafkaMsgPojo();
        kafkaMsgPojo.setTopic(this.topic);
        kafkaMsgPojo.setTime(record.timestamp()+"");
        kafkaMsgPojo.setMsgKey(record.key()==null?0:record.key());
        kafkaMsgPojo.setMsgValue(record.value());
        kafkaMsgPojo.setOffset(record.offset());
        kafkaMsgPojo.setPartition(record.partition());
        return kafkaMsgPojo;
    }
}
