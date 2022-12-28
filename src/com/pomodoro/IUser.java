package com.pomodoro;

public interface IUser {

    boolean logIn();
    boolean correctUsername(String username);

    void createNewAccount();
    void continueAsGuest();
    void userSettings(String username);

}
