package com.hyd.kafkaclient.gui.window;

import com.hyd.kafkaclient.gui.panel.BodySection;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel
{
    private BodySection bodySection;

    public MainView() {
        createUI();
    }


    public void createUI()
    {
        setLayout(new BorderLayout(0, 0));
        createBodySection();
    }

    public void createMenuSection()
    {

    }

    public void createBodySection()
    {
        bodySection = new BodySection();
        add(bodySection, BorderLayout.CENTER);
    }

    public void createStatusSection()
    {

    }

    public BodySection getBodySection()
    {
        return bodySection;
    }
}

