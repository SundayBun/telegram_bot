package com.example.telegram_app.cache;

import com.example.telegram_app.bot_api.BotState;
import com.example.telegram_app.handlers.UserProfileData;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class UserDataCache implements IDataCache {

    private Map<Long, BotState> userBotStates = new HashMap<>();
    private Map<Long, UserProfileData> userProfileData = new HashMap<>();

//    @Override
//    public BotState getUserCurrentBotState(long userID) {
//
//        BotState botState = userBotStates.get(userID);
//
//        if (botState == null) {
//            botState = BotState.ASK_DESTINY;
//        }
//        return botState;
//    }

    @Override
    public UserProfileData getUserProfile(long chartID) {
        return  userProfileData.get(chartID);
    }

    @Override
    public void setUserCurrentBotState(long userID, BotState botState) {
        userBotStates.put(userID, botState);
    }

    @Override
    public void saveUserProfileData(long chartID, UserProfileData userProfile) {
        userProfileData.put(chartID, userProfile);
    }

//    @Override
//    public void updateUserProfileData(UserProfileData userProfileData) {
//
//    }
}
