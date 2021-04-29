package com.example.telegram_app.bot_api;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonHandler {

    public static InlineKeyboardMarkup getMessageAskDestiny() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();


//creating the buttons
        InlineKeyboardButton inlineKeyboardButtonYes = new InlineKeyboardButton();
        inlineKeyboardButtonYes.setText("Yes");
        InlineKeyboardButton inlineKeyboardButtonNo = new InlineKeyboardButton();
        inlineKeyboardButtonNo.setText("No");

        //Every button must have callBackData, or else it will not work !
        inlineKeyboardButtonYes.setCallbackData("Button \"Yes\" has been pressed");
        inlineKeyboardButtonNo.setCallbackData("Button \"No\" has been pressed");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButtonYes);
        keyboardButtonsRow1.add(inlineKeyboardButtonNo);

        List< List<InlineKeyboardButton>> keyboardButtonsRow=new ArrayList<>();
        keyboardButtonsRow.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(keyboardButtonsRow);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getMessageAskGender() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        //creating the buttons
        InlineKeyboardButton inlineKeyboardButtonFem = new InlineKeyboardButton();
        inlineKeyboardButtonFem.setText("Female");
        InlineKeyboardButton inlineKeyboardButtonMale = new InlineKeyboardButton();
        inlineKeyboardButtonMale.setText("Male");

        //Every button must have callBackData, or else it will not work !
        inlineKeyboardButtonFem.setCallbackData("Button \"Female\" has been pressed");
        inlineKeyboardButtonMale.setCallbackData("Button \"Male\" has been pressed");

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(inlineKeyboardButtonFem);
        keyboardButtonsRow.add(inlineKeyboardButtonMale);

        List< List<InlineKeyboardButton>> keyboardList=new ArrayList<>();
        keyboardList.add(keyboardButtonsRow);

        inlineKeyboardMarkup.setKeyboard(keyboardList);
        return inlineKeyboardMarkup;

    }
}
