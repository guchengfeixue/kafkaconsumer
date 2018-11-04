package com.hyd.kafkaclient.kafka;

public class KafkaMsgPojo
{
    private String topic;
    private long offset;
    private int partition;
    private int msgKey;
    private String msgValue;
    private String time;

    public String getTopic()
    {
        return topic;
    }

    public void setTopic(String topic)
    {
        this.topic = topic;
    }

    public long getOffset()
    {
        return offset;
    }

    public void setOffset(long offset)
    {
        this.offset = offset;
    }

    public int getPartition()
    {
        return partition;
    }

    public void setPartition(int partition)
    {
        this.partition = partition;
    }

    public int getMsgKey()
    {
        return msgKey;
    }

    public void setMsgKey(int msgKey)
    {
        this.msgKey = msgKey;
    }

    public String getMsgValue()
    {
        return msgValue;
    }

    public void setMsgValue(String msgValue)
    {
        this.msgValue = msgValue;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
