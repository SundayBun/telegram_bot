package com.example.telegram_app.bot_api;

import com.example.telegram_app.MyWizardTelegramBot;
import com.example.telegram_app.handlers.UserProfileData;
import com.example.telegram_app.service.ReplyMessageService;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
//@Component
@Getter
public class BotStateContext {
    private final MyWizardTelegramBot bot;
    private final UserProfileData user; // entity сущность пользователя
    private final String input; //то что пользователь вводит в ответ на вопрос, формируется в MyWizardTelegramBot
    private CallbackQuery callbackQuery=null;

    private BotStateContext(MyWizardTelegramBot bot, UserProfileData user, String input) {
        this.bot = bot;
        this.user = user;
        this.input = input;
    }

    private BotStateContext(MyWizardTelegramBot bot, UserProfileData user, String input, CallbackQuery callbackQuery) {
        this.bot = bot;
        this.user = user;
        this.input = input;
        this.callbackQuery = callbackQuery;
    }



    public static BotStateContext of(MyWizardTelegramBot bot, UserProfileData user, String text,CallbackQuery callbackQuery) {
        return new BotStateContext(bot, user, text,callbackQuery);
    }
}
