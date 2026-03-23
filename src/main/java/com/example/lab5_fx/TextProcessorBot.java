package com.example.lab5_fx;

import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import com.example.lab5_fx.BotConfig;
import com.example.lab5_fx.TextProcessor;

public class TextProcessorBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;
    private final TextProcessor processor;

    public TextProcessorBot() {
        telegramClient = new OkHttpTelegramClient(BotConfig.BOT_TOKEN);
        processor = new TextProcessor();
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String answerText;
            if (userMessageText.equals("/start")) {
                answerText = "Привет! Я шифрующий бот Цезаря. Просто отправьте мне текст.";
            } else if (userMessageText.equals("/help")) {
                answerText = "Я шифрую текст с использованием шифра Цезаря со сдвигом 3.";
            } else {
                answerText = processor.caesarEncrypt(userMessageText);
            }

            SendMessage reply = SendMessage.builder()
                    .chatId(chatId)
                    .text(answerText)
                    .build();

            try {
                telegramClient.execute(reply);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}