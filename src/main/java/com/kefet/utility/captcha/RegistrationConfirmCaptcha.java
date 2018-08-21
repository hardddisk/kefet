package com.kefet.utility.captcha;

public class RegistrationConfirmCaptcha {

    private String elegibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_1234567890";
    private char[] chars = elegibleChars.toCharArray();
    
    private char[] numchars = "1234567890".toCharArray();

    private String finalString = "";

    public String generateKey() {
        for (int i = 0; i < 7; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (chars.length - 1));
            char characterToShow = chars[randomIndex];
            finalString += characterToShow;
        }
        return finalString;
    }

    

    public String generate6RandomNumbers() {
        for (int i = 0; i < 6; i++) {
            double randomValue = Math.random();
            int randomIndex = (int) Math.round(randomValue * (numchars.length - 1));
            char characterToShow = numchars[randomIndex];
            finalString += characterToShow;
        }
        return finalString;
    }

}
