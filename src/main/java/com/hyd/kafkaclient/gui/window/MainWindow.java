package com.hyd.kafkaclient.gui.window;

import com.hyd.kafkaclient.constant.UIConst;

import javax.swing.*;
import java.awt.*;

public class MainWindow
{
    private JFrame frame;
    private MainView mainView;

    public MainWindow()
    {
        createUI();
    }

    private void createUI()
    {
        System.out.println("MainWindow createUI");
        frame = new JFrame(UIConst.MAIN_WINDOW_TITLE);
        frame.setVisible(false);
        frame.setMinimumSize(new Dimension(UIConst.MAIN_WINDOW_WIDTH, UIConst.MAIN_WINDOW_HEIGHT));
        frame.setLayout(new BorderLayout());

        mainView = new MainView();

        frame.add(mainView, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void show()
    {
        frame.setVisible(true);
    }

    public MainView getMainView()
    {
        return mainView;
    }
}
