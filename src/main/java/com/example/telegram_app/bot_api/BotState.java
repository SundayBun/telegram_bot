package com.example.telegram_app.bot_api;

import com.example.telegram_app.cache.IDataCache;
import com.example.telegram_app.handlers.UserProfileData;
import com.example.telegram_app.service.ReplyMessageService;
import com.example.telegram_app.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Возможные состояния бота
 */
@Component
@PropertySource("classpath:messages_Eng.properties")
public enum BotState {
    ASK_DESTINY {

        private BotState next;

        @Override
        public SendMessage enter(BotStateContext context) {
            return sendQuery(context,"Do you want to know your today's destiny");
                    //replyMessageService.getReplyText("reply.askDestiny"));
        }

        @Override
        public void handleInput(BotStateContext context) {

            if (context.getCallbackQuery().getData().equals("Button \"Yes\" has been pressed")) {

                next = ASK_NAME;
            } else {
                next = ASK_DESTINY;
            }
            context.getUser().setStateId(ASK_DESTINY.ordinal());
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    ASK_NAME {
        @Override
        public SendMessage enter(BotStateContext context) {
            return sendMessage(context, "What is your name?");
            //replyMessageService.getReplyText("reply.askName"));
        }

        @Override
        public void handleInput(BotStateContext context) {
            context.getUser().setStateId(ASK_NAME.ordinal());
            context.getUser().setName(context.getInput());
        }

        @Override
        public BotState nextState() {
            return ASK_AGE;
        }
    },
    ASK_AGE {
        private BotState next;

        @Override
        public SendMessage enter(BotStateContext context) {
            return sendMessage(context, "How old are you?");
            //replyMessageService.getReplyText("reply.askAge"));
        }

        @Override
        public void handleInput(BotStateContext context) {
            if (!UserValidator.isValidAge(context.getInput())) {
                next = ASK_AGE;
            } else {
                context.getUser().setStateId(ASK_AGE.ordinal());
                context.getUser().setAge(Integer.parseInt(context.getInput()));
                next = ASK_GENDER;
            }
        }

        @Override
        public BotState nextState() {
            return next;
        }
    },

    ASK_GENDER {
        @Override
        public SendMessage enter(BotStateContext context) {
            return sendQuery(context, "What is your gender?");
            //return sendMessage(context, "What is your gender?");
            //replyMessageService.getReplyText("reply.askGender"));
        }

        @Override
        public void handleInput(BotStateContext context) {
            context.getUser().setStateId(ASK_GENDER.ordinal());

            if (context.getCallbackQuery().getData().equals("Button \"Female\" has been pressed")) {

                context.getUser().setGender("Female");
            } else {
            context.getUser().setGender("Male");}

            context.getUser().setStateId(ASK_GENDER.ordinal());
        }

        @Override
        public BotState nextState() {
            return PROFILE_FILLED;
        }
    },
    PROFILE_FILLED(false) {
        @Override
        public SendMessage enter(BotStateContext context) {
            return sendMessage(context, "Thank you for your application. This is your today's destiny");
            //replyMessageService.getReplyText("reply.filled"));
        }

        @Override
        public void handleInput(BotStateContext context) {
            context.getUser().setStateId(PROFILE_FILLED.ordinal());
        }

        @Override
        public BotState nextState() {
            return ASK_DESTINY;
        }
    };
//    SHOW_USER_PROFILE,
//    SHOW_MAIN_MENU,
//    SHOW_HELP_MENU;

    private static BotState[] states;
    private final boolean inputNeeded; // ждем ли действий от пользователя


   // @Autowired
  //  ReplyMessageService replyMessageService;
//
//    @Autowired
//    IDataCache currentUserCache;
//
//    @Autowired
//   // UserProfileData currentUserData;


    BotState() {
        this.inputNeeded = true; // по умолчанию- ждем действия
    }

    BotState(boolean inputNeeded) {
        this.inputNeeded = inputNeeded; // или задаем самостоятельно
    }

    public static BotState getInitialState() { // возвращает первоначальное состояние, в которое надо перейти при регистрации нового пользователя
        return byId(0);
    }

    public static BotState byId(int id) {

        if (states == null) { //если states еще не инициализирован
            states = BotState.values(); //массиву states передаем все значения enum-а
        }
        return states[id]; //возвращаем значение по индексу
    }

    protected SendMessage sendMessage(BotStateContext context, String text) {
        SendMessage message = new SendMessage();

        message.setChatId(context.getUser().getChatId().toString());
        message.setText(text);

//        try {
//            context.getBot().execute(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
        return message;
    }

    protected SendMessage sendQuery(BotStateContext context, String text) {
        SendMessage message = new SendMessage();

        message.setChatId(context.getUser().getChatId().toString());
        message.setText(text);
        if (context.getUser().getStateId() == ASK_DESTINY.ordinal()) {
            message.setReplyMarkup(ButtonHandler.getMessageAskDestiny());
        } else {
            message.setReplyMarkup(ButtonHandler.getMessageAskGender());
        }

        return message;
    }


    public boolean isInputNeeded() {
        return inputNeeded;
    }

    public void handleInput(BotStateContext context) { // обрабатывает ввод пользователя в текущем состоянии
        //когда пользователь что-то посылает срабатывает метод handleInput
        // do nothing by default
    }

    public abstract SendMessage enter(BotStateContext context); //войти в состояние

    public abstract BotState nextState(); //говорит в какое состояние переходить, когда текущее уже обработанно
}

