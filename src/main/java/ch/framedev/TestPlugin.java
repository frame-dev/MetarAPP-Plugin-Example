package ch.framedev;



/*
 * ch.framedev
 * =============================================
 * This File was Created by FrameDev
 * Please do not change anything without my consent!
 * =============================================
 * This Class was created at 30.05.2025 23:38
 */

import ch.framedev.metarapp.events.EventBus;
import ch.framedev.metarapp.util.Plugin;

public class TestPlugin implements Plugin {

    @Override
    public void initialize() {
        System.out.println("TestPlugin initialized!");
    }

    @Override
    public void start() {
        System.out.println("TestPlugin started!");

        EventBus.registerDatabaseSendListener(event -> {
            System.out.println("Plugin received database send event: " + event.getDatabaseName() + " - " + event.getQuery());
        });
        EventBus.registerDisplayMetarListener(event -> {
            System.out.println("Plugin received METAR: " + event.getIcao() + " - " + event.getData());
        });
        EventBus.registerDownloadedFileListener(event -> {
            System.out.println("Plugin received downloaded file: " + event.getFilePath());
        });
        EventBus.registerLoginListener(event -> {
            if(event.isSuccess())
                System.out.println("Plugin received login success for user: " + event.getUsername());
            else
                System.out.println("Plugin received login failure for user: " + event.getUsername());
        });
        EventBus.registerRefreshListener(event -> {
            System.out.println("Plugin received refresh from " + event.getFrom() + " to " + event.getTo());
        });
        EventBus.registerSendIcaoListeners(event -> {
            System.out.println("Plugin received send ICAO event: " + event.getIcao());
        });
    }

    @Override
    public void stop() {
        System.out.println("TestPlugin stopped!");
    }

    @Override
    public String getName() {
        return "TestPlugin";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public String getDescription() {
        return "Test Plugin for MetarApp";
    }

    @Override
    public String getAuthor() {
        return "FrameDev";
    }

    @Override
    public String getWebsite() {
        return "";
    }

    @Override
    public String getNewVersion() {
        return "";
    }

    @Override
    public String getDownloadLink() {
        return "";
    }
}
