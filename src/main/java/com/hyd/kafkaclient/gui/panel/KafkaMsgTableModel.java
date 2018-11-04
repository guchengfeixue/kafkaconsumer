package com.hyd.kafkaclient.gui.panel;

import com.hyd.kafkaclient.Application;
import com.hyd.kafkaclient.kafka.KafkaDataManager;
import com.hyd.kafkaclient.kafka.KafkaMsgPojo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KafkaMsgTableModel extends AbstractTableModel
{
    private static final String[] columnNames = {"topic", "timestamp", "offset", "partition", "message key", "message value"};
    private List<List<Object>> data = new ArrayList<>();
    private String showTopic;

    public KafkaMsgTableModel()
    {
    }

    public void loadData()
    {
        String curTopic = Application.getInstance().getCurrentTopic();
        data = new ArrayList<>();
        if(null == curTopic)
        {
            return;
        }
        showTopic = curTopic;
        List<KafkaMsgPojo> msgPojos = KafkaDataManager.getInstance().getTopicMsg(curTopic);
        msgPojos.forEach(msg->data.add(Arrays.asList(
                msg.getTopic(), msg.getTime(), msg.getOffset(), msg.getPartition(), msg.getMsgKey(), msg.getMsgValue())));
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public int getRowCount()
    {
        return data.size();
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        try
        {
            return data.get(rowIndex).get(columnIndex);
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Exception out of bounds row_index="+rowIndex+", column_index="+columnIndex+", data_size="+data.size());
            throw e;
        }
    }
}
