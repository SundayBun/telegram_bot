package com.example.telegram_app.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    public static boolean isValidAge(String age){
        int ageInt=0;
       if (age==null) return false;
       try{
           ageInt=Integer.parseInt(age);
       } catch (NumberFormatException nfe){
           return false;
       }
       return ageInt>0;
    }
}
