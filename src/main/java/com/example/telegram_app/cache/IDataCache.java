package com.example.telegram_app.cache;

import com.example.telegram_app.bot_api.BotState;
import com.example.telegram_app.handlers.UserProfileData;

public interface IDataCache {

  //  public BotState getUserCurrentBotState(long userID);

    public UserProfileData getUserProfile(long chatID);

    public void setUserCurrentBotState(long userID, BotState botState);

    public void saveUserProfileData(long chatID,UserProfileData userProfileData);

   // public void updateUserProfileData(UserProfileData userProfileData);

}
