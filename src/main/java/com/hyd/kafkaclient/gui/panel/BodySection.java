package com.hyd.kafkaclient.gui.panel;

import com.hyd.kafkaclient.Application;
import com.hyd.kafkaclient.gui.window.DetailPopWindow;
import com.hyd.kafkaclient.kafka.KafkaDataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BodySection extends JPanel
{
    private JTable jTable;
    private KafkaMsgTableModel kafkaMsgTableModel;

    public BodySection()
    {
        kafkaMsgTableModel = new KafkaMsgTableModel();
        createUI();
    }

    private void createUI()
    {
        jTable = new JTable();
        jTable.setModel(kafkaMsgTableModel);
        jTable.setShowGrid(true);
        addCellDoubleClickListener();

        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);

        setLayout(new BorderLayout());
        add(jScrollPane, BorderLayout.CENTER);
    }

    private void addCellDoubleClickListener()
    {
        jTable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if(e.getClickCount() == 2)
                {
                    onCellDoubleClicked();
                }
            }
        });
    }

    private void onCellDoubleClicked()
    {
        int row = jTable.getSelectedRow();
        String msgValue = jTable.getValueAt(row, kafkaMsgTableModel.getColumnCount() - 1).toString();
        System.out.println("selected row:"+msgValue);
        DetailPopWindow detailPopWindow = new DetailPopWindow();
        String topic = Application.getInstance().getCurrentTopic();
        detailPopWindow.show(KafkaDataManager.getInstance().getTopicMsg(topic).get(row));
    }

    public void updateView()
    {
        kafkaMsgTableModel.loadData();
        jTable.updateUI();
    }
}

