package com.example.telegram_app.handlers;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;


/**
 * Данные анкеты пользователя
 */

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class UserProfileData {
    private Long id;
    private Long chatId;

    private Integer stateId;
    private String name;
    private int age;
    private String gender;

    public UserProfileData() {
    }

    public UserProfileData(Long chatId,Integer stateId){
        this.chatId=chatId;
        this.stateId=stateId;
    }

}
