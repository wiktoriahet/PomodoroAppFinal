package com.pomodoro;

public interface IWritingService {

    String NEW_LINE = "\n";

    void printMsg(StringBuilder msg);
    void menuMessage();
    void userMenuMessage(String username);
    void activityMenuMessage(String activityName);
    void guestMenuMsg();
    void newPomodoroSession();
    void newWorkSession();
    void newBreakSession();
    void userSettingsMsg();

    String typeNewPassword();
    String typeUserName();
    String typeNewUsername();
    String typePassword();
    String typeActivityName();
    String typeToStart();


    int choice();



}
