package com.example.telegram_app;

import com.example.telegram_app.bot_api.BotState;
import com.example.telegram_app.bot_api.TelegramFacade;
import com.example.telegram_app.service.ReplyMessageService;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


/**
 * https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started
 */

//класс, который передает инфу о нашем боте на сервера телеграмма, чтобы авторизоваться
@Getter
@Setter
@PropertySource("classpath:application.properties")
@Component
public class MyWizardTelegramBot extends TelegramWebhookBot {

    @Value("${telegrambot.webHookPath}")
    private String webHookPath;
    @Value("${telegrambot.botuserName}")
    private String botUserName;
    @Value("${telegrambot.botToken}")
    private String botToken;

@Autowired
    private TelegramFacade telegramFacade;
    private BotState botState;

    //This method must always return your Bot username
    @Override
    public String getBotUsername() {
        return botUserName;
    }

    //This method must always return your Bot Token (If you don't know it, you may want to talk with @BotFather)
    @Override
    public String getBotToken() {
        return botToken;
    }

    //метод, который вызывается автоматически, когда пользователь что-то вводит
    //объект update содержит chatId и данные(сообщение), которые пользователь ввел

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null ||update.hasCallbackQuery()|| update.getMessage().hasText()) {
            // long chat_ID=update.getMessage().getChatId(); // получем текущий chatId
             //telegramFacade.handleUpdate(update,this); // Call method to send the message

           // SendMessage message =  telegramFacade.handleUpdate(update,this);
            SendMessage message = new SendMessage();
            message=telegramFacade.handleUpdate(update,this);



            //SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory(обязательными) fields
            //message.setChatId(update.getMessage().getChatId().toString());//передаем объекту, отправляющему сообщение клиенту chatId
           // message.setText(update.getMessage().getText()); //передаем объекту, отправляющему сообщение клиенту сообщение

            return message;

//            try {
//                execute(message); // Call method to send the message
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }

        }

        return null;
    }

    @Override
    public String getBotPath() {
        return webHookPath; //произволный путь чтобы доставить обновленную информацию
    }
}
