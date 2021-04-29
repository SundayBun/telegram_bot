package com.example.telegram_app.controller;

import com.example.telegram_app.MyWizardTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

//класс, обрабатывающий get и post запросы
@RestController //@ResponseBody ()+@Controller
@ResponseBody
public class WebHookController {

    private final MyWizardTelegramBot myWizardTelegramBot;

    @Autowired
    public WebHookController(MyWizardTelegramBot myWizardTelegramBot) {
        this.myWizardTelegramBot = myWizardTelegramBot;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> updatesReceived(@RequestBody Update update) { //@RequestBody - получает все тело запроса (в отличии от @RequestParam, который запрашивает парам отдельно)
      return   myWizardTelegramBot.onWebhookUpdateReceived(update);
    }
}
