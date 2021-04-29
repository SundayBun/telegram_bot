package com.example.telegram_app.appConfig;

import com.example.telegram_app.MyWizardTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ComponentScan("com.example.telegram_app")

public class BotConfig {

    MyWizardTelegramBot myWizardTelegramBot;

    @Autowired
    public BotConfig(MyWizardTelegramBot myWizardTelegramBot) {
        this.myWizardTelegramBot = myWizardTelegramBot;
    }

//    private String webHookPath;
//    private String botUserName;
//    private String botToken;
//
//    @Bean
//    public MyWizardTelegramBot myWizardTelegramBot() {
//
//        MyWizardTelegramBot myWizardTelegramBot = new MyWizardTelegramBot();
//        myWizardTelegramBot.setBotUserName(botUserName);
//        myWizardTelegramBot.setBotToken(botToken);
//        myWizardTelegramBot.setWebHookPath(webHookPath);
//
//        return myWizardTelegramBot;
//    }
//    @Bean
//    //добавление сообщений из property файла в любое место по этому бину
//    public MessageSource messageSource(){
//        ReloadableResourceBundleMessageSource messageSource=new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
}
