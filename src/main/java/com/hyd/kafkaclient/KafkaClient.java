package com.hyd.kafkaclient;


import com.hyd.kafkaclient.ssh.SSh;

import java.rmi.server.ExportException;

public class KafkaClient
{
    public static void main( String[] args )
    {
//        Application app = Application.getInstance();
//        app.run();
        SSh sshAgent = new SSh();
        try
        {
            sshAgent.initSession("47.98.248.164", "root", "ABCDabcd123");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        sshAgent.execCommand();
    }
}


