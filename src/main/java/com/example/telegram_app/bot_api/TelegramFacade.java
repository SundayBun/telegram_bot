package com.example.telegram_app.bot_api;

import com.example.telegram_app.MyWizardTelegramBot;
import com.example.telegram_app.cache.IDataCache;
import com.example.telegram_app.handlers.UserProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramFacade {

    //   private static final Logger LOGGER= LogManager.getLogManager().getLogger();
    private BotState state;
    private BotStateContext context;
    @Autowired
    private IDataCache dataCache;
    SendMessage sendMessage;

    private CallbackQuery callbackQuery;
    private long chatId;
    private String text;


    public SendMessage handleUpdate(Update update, MyWizardTelegramBot bot) {
        if (update.hasCallbackQuery()) {
            callbackQuery = update.getCallbackQuery();
        } else {
            chatId = update.getMessage().getChatId();
            text = update.getMessage().getText();
        }

//        final long chatId = update.getMessage().getChatId();
//        final String text = update.getMessage().getText();
        // final CallbackQuery callbackQuery = update.getCallbackQuery();

        UserProfileData user = dataCache.getUserProfile(chatId);

        if (user == null) {

            state = BotState.getInitialState();
            user = new UserProfileData(chatId, state.ordinal());
            dataCache.saveUserProfileData(chatId, user);
            context = BotStateContext.of(bot, user, text, callbackQuery);
            sendMessage = state.enter(context);
            return sendMessage;

        } else {
            context = BotStateContext.of(bot, user, text, callbackQuery);
            state = BotState.byId(user.getStateId());
        }

        state.handleInput(context);

        do {
            state = state.nextState();
            sendMessage = state.enter(context);
        } while (!state.isInputNeeded());

        user.setStateId(state.ordinal());
        dataCache.saveUserProfileData(chatId, user);
        return sendMessage;
    }

}