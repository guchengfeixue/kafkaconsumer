package com.hyd.kafkaclient.gui.window;

import com.hyd.kafkaclient.constant.UIConst;
import com.hyd.kafkaclient.kafka.KafkaMsgPojo;

import javax.swing.*;
import java.awt.*;

public class DetailPopWindow
{
    private JFrame frame;
    private DetailPopView detailPopView;

    public DetailPopWindow()
    {
        createUI();
    }

    private void createUI()
    {
        System.out.println("DetailPopWindow createUI");
        frame = new JFrame(UIConst.DETAIL_WINDOW_TITLE);
        frame.setVisible(false);
        frame.setMinimumSize(new Dimension(UIConst.DETAIL_WINDOW_WIDTH, UIConst.DETAIL_WINDOW_HEIGHT));
        frame.setLayout(new BorderLayout());

        detailPopView = new DetailPopView();

        frame.add(detailPopView, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void show(KafkaMsgPojo kafkaMsgPojo)
    {
        frame.setVisible(true);
        detailPopView.setKafkaMsg(kafkaMsgPojo);
    }

    public DetailPopView getDetailPopView()
    {
        return detailPopView;
    }
}
