package com.pomodoro;

public interface IPassword {

    boolean isPasswordCorrect(String userName, String password);
    void changePassword(String username);

}
