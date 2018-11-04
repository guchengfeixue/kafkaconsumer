package com.hyd.kafkaclient.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaDataManager
{
    private static KafkaDataManager kafkaDataManager = new KafkaDataManager();

    private Map<String, List<KafkaMsgPojo>> topicData;

    public static KafkaDataManager getInstance()
    {
        return kafkaDataManager;
    }

    private KafkaDataManager()
    {
        topicData = new HashMap<>();
    }

    public void addTopicMsg(String topic, KafkaMsgPojo msg)
    {
        if(!topicData.containsKey(topic))
        {
            topicData.put(topic, new ArrayList<>());
        }
        topicData.get(topic).add(msg);
    }

    public void addTopic(String topic)
    {
        if(!topicData.containsKey(topic))
        {
            topicData.put(topic, new ArrayList<>());
        }
    }

    public boolean hasTopic(String topic)
    {
        return topicData.containsKey(topic);
    }

    public List<KafkaMsgPojo> getTopicMsg(String topic)
    {
        if(!topicData.containsKey(topic))
        {
            return null;
        }
        return topicData.get(topic);
    }

    public Map<String, List<KafkaMsgPojo>> getTopicData()
    {
        return topicData;
    }

    public void setTopicData(Map<String, List<KafkaMsgPojo>> topicData)
    {
        this.topicData = topicData;
    }
}
