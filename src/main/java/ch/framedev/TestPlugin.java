package ch.framedev;



/*
 * ch.framedev
 * =============================================
 * This File was Created by FrameDev
 * Please do not change anything without my consent!
 * =============================================
 * This Class was created at 30.05.2025 23:38
 */

import ch.framedev.metarapp.events.*;
import ch.framedev.metarapp.util.Plugin;

import java.util.function.Consumer;

public class TestPlugin implements Plugin {

    private Consumer<CallRefreshEvent> refreshListener;
    private Consumer<SendIcaoEvent> sendIcaoListener;
    private Consumer<DownloadedFileEvent> downloadedFileListener;
    private Consumer<DisplayMetarEvent> displayMetarListener;
    private Consumer<LoginEvent> loginListener;
    private Consumer<LogoutEvent> logoutListener;
    private Consumer<DatabaseSendEvent> databaseSendListener;
    private Consumer<DatabaseErrorEvent> databaseErrorListener;
    private Consumer<ErrorEvent> errorListener;

    @Override
    public void initialize() {
        System.out.println("TestPlugin initialized!");
    }

    @Override
    public void start() {
        System.out.println("TestPlugin started!");

        // Registering event listeners
        logoutListener = event -> {
            System.out.println("Plugin received logout event for user: " + event.getUsername());
            event.setMessage("Fuck you for using MetarApp!");
        };
        EventBus.registerLogoutListener(logoutListener);

        errorListener = event -> {
            System.out.println("⚠️ Plugin received an error:");
            System.out.println("Message: " + event.getMessage());
            System.out.println("Cause: " + event.getErrorCode());
        };
        EventBus.registerErrorListener(errorListener);

        databaseErrorListener = event -> {
            System.out.println("⚠️ Plugin received a database error:");
            System.out.println("Database: " + event.getDatabaseName());
            System.out.println("Message: " + event.getErrorMessage());
            System.out.println("Cause: " + event.getError().getLocalizedMessage());
        };
        EventBus.registerDatabaseErrorListener(databaseErrorListener);

        databaseSendListener = event -> {
            System.out.println("Plugin received database send event: " + event.getDatabaseName() + " - " + event.getQuery());
        };
        EventBus.registerDatabaseSendListener(databaseSendListener);

        displayMetarListener = event -> {
            System.out.println("Plugin received METAR: " + event.getIcao() + " - " + event.getData());
        };
        EventBus.registerDisplayMetarListener(displayMetarListener);

        downloadedFileListener = event -> {
            System.out.println("Plugin received downloaded file: " + event.getFilePath());
        };
        EventBus.registerDownloadedFileListener(downloadedFileListener);

        loginListener = event -> {
            if (event.isSuccess()) {
                System.out.println("Plugin received login success for user: " + event.getUsername());
            } else {
                System.out.println("Plugin received login failure for user: " + event.getUsername());
            }
        };
        EventBus.registerLoginListener(loginListener);

        refreshListener = event -> {
            System.out.println("Plugin received refresh from " + event.getFrom() + " to " + event.getTo());
        };
        EventBus.registerRefreshListener(refreshListener);

        sendIcaoListener = event -> {
            System.out.println("Plugin received send ICAO event: " + event.getIcao());
        };
        EventBus.registerSendIcaoListeners(sendIcaoListener);
    }

    @Override
    public void stop() {
        System.out.println("TestPlugin stopped!");

        EventBus.unregisterDatabaseErrorListener(databaseErrorListener);
        EventBus.unregisterDatabaseSendListener(databaseSendListener);
        EventBus.unregisterDisplayMetarListener(displayMetarListener);
        EventBus.unregisterDownloadedFileListener(downloadedFileListener);
        EventBus.unregisterLoginListener(loginListener);
        EventBus.unregisterRefreshListener(refreshListener);
        EventBus.unregisterSendIcaoListener(sendIcaoListener);
        EventBus.unregisterErrorListener(errorListener);
        EventBus.unregisterLogoutListener(logoutListener);
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
