package com.example.telegram_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

//Формирует готовые ответные сообщения в чат
@Service
@Component
public class ReplyMessageService {
    LocaleMessageService localeMessageService;

    @Autowired
    public ReplyMessageService(LocaleMessageService localeMessageService) {
        this.localeMessageService = localeMessageService;
    }

    public SendMessage getReplyMessage(String chatId, String replyMessage) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage));
    }

    public SendMessage getReplyMessage(String chatId, String replyMessage,Object...args) {
        return new SendMessage(chatId, localeMessageService.getMessage(replyMessage, args));
    }
    public String getReplyText(String replyText) {
        return localeMessageService.getMessage(replyText);
    }
}
