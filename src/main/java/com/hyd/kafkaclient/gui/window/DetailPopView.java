package com.hyd.kafkaclient.gui.window;

import com.hyd.kafkaclient.kafka.KafkaMsgPojo;

import javax.swing.*;
import java.awt.*;

public class DetailPopView extends JPanel
{
    private JTextArea jTextArea;

    public DetailPopView()
    {
        createUI();
    }

    public void createUI()
    {
        setLayout(new BorderLayout());
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        add(new JScrollPane(jTextArea), BorderLayout.CENTER);
    }

    public void setKafkaMsg(KafkaMsgPojo kafkaMsgPojo)
    {
        jTextArea.setText(kafkaMsgPojo.getMsgValue());
    }
}
