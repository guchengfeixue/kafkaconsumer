package com.hyd.kafkaclient;

import com.hyd.kafkaclient.gui.window.MainWindow;
import com.hyd.kafkaclient.kafka.Consumer;
import com.hyd.kafkaclient.kafka.KafkaProperties;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application
{
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    private String currentTopic;

    private static Application application = new Application();

    private MainWindow mainWindow;

    public static Application getInstance()
    {
        return application;
    }

    private void configLog()
    {
        PropertyConfigurator.configure("src/main/resources/conf/log.conf");
    }

    private Application()
    {
        configLog();
        mainWindow = new MainWindow();
    }

    public void run()
    {
        this.mainWindow.getMainView().getBodySection().updateView();
        mainWindow.show();
        startConsumer();
    }

    public void startConsumer()
    {
        setCurrentTopic(KafkaProperties.TOPIC);
        Consumer consumer = new Consumer(KafkaProperties.TOPIC);
        consumer.start();
    }

    public String getCurrentTopic()
    {
        return currentTopic;
    }

    public void setCurrentTopic(String currentTopic)
    {
        this.currentTopic = currentTopic;
    }

    public void onMsgUpdate()
    {
        this.mainWindow.getMainView().getBodySection().updateView();
    }
}
