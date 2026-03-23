package com.example.lab5_fx;

import javafx.application.Application;
import javafx.stage.Stage;
import com.example.lab5_fx.TextProcessorBot;
import com.example.lab5_fx.BotConfig;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TextBotApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TG Bot");
        primaryStage.show();

        new Thread(() -> {
            try {
                TextProcessorBot bot = new TextProcessorBot();
                TelegramBotsLongPollingApplication botsApp = new TelegramBotsLongPollingApplication();
                botsApp.registerBot(BotConfig.BOT_TOKEN, bot);
                System.out.println("Телеграм бот запущен!");
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}