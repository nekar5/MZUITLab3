package org.example;

import org.fluentd.logger.FluentLogger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LogBot extends TelegramLongPollingBot {

    private static final FluentLogger logger = FluentLogger.getLogger(null, "localhost", 8080);

    public static void main(String[] args) throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(new LogBot());
        System.out.println("Bot started");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            int userId = Math.toIntExact(update.getMessage().getFrom().getId());
            String username = update.getMessage().getFrom().getUserName();
            String message = update.getMessage().getText();

            Map<String, Object> data = new HashMap<>();
            data.put("userId", userId);
            data.put("username", username);
            data.put("message", message);
            data.put("@timestamp", ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
            System.out.println(message);
            logger.log("telegram.bot", data);
        }
    }

    @Override
    public String getBotUsername() {
        return "---";
    }

    @Override
    public String getBotToken() {
        return "---";
    }
}
