package com.pomodoro;

import java.util.Scanner;

public class WritingService implements IWritingService{

    Scanner input = new Scanner(System.in);


    @Override
    public void printMsg(StringBuilder msg) {
        System.out.println(msg);
    }

    @Override
    public void menuMessage() {

        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("WELCOME IN POMODORO APP!");
        msg.append(NEW_LINE);
        msg.append("(1). LOG IN");
        msg.append(NEW_LINE);
        msg.append("(2). CREATE NEW ACCOUNT");
        msg.append(NEW_LINE);
        msg.append("(3). CONTINUE AS A GUEST");
        msg.append(NEW_LINE);
        msg.append("(4). INFORMATION ABOUT POMODORO APP");
        msg.append(NEW_LINE);
        msg.append("(5). EXIT");

        printMsg(msg);
    }

    @Override
    public void userMenuMessage(String username) {

        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("WELCOME ").append(username);
        msg.append(NEW_LINE);
        msg.append("(1). CREATE NEW ACTIVITY");
        msg.append(NEW_LINE);
        msg.append("(2). CHOOSE ACTIVITY");
        msg.append(NEW_LINE);
        msg.append("(3). YOUR STATISTICS");
        msg.append(NEW_LINE);
        msg.append("(4). DELETE ACTIVITY");
        msg.append(NEW_LINE);
        msg.append("(5). ACCOUNT SETTINGS");
        msg.append(NEW_LINE);
        msg.append("(6). EXIT");

        printMsg(msg);
    }

    @Override
    public void activityMenuMessage(String activityName) {

        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("YOU ARE IN A MENU FOR: " + activityName);
        msg.append(NEW_LINE);
        msg.append("(1). DEFAULT POMODORO SESSION");
        msg.append(NEW_LINE);
        msg.append("(2). CUSTOM POMODORO SESSION");
        msg.append(NEW_LINE);
        msg.append("(3). EXIT");

        printMsg(msg);
    }

    @Override
    public void guestMenuMsg() {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("GUEST MENU");
        msg.append(NEW_LINE);
        msg.append("(1). DEFAULT POMODORO SESSION");
        msg.append(NEW_LINE);
        msg.append("(2). CUSTOM POMODORO SESSION");
        msg.append(NEW_LINE);
        msg.append("(3). EXIT");

        printMsg(msg);
    }

    @Override
    public void newPomodoroSession() {

        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("Your Pomodoro session has begun!");
        printMsg(msg);

    }

    @Override
    public void newWorkSession() {

        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("Work session!");
        printMsg(msg);

    }

    @Override
    public void newBreakSession() {

        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("Break session!");
        printMsg(msg);

    }

    @Override
    public void userSettingsMsg() {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append("What do you want to do?");
        msg.append(NEW_LINE);
        msg.append("(1). Change password");
        msg.append(NEW_LINE);
        msg.append("(2). Change username");
        printMsg(msg);
    }

    @Override
    public String typeUserName() {

        System.out.println("Enter username");
        return input.nextLine();
    }

    @Override
    public String typeNewUsername() {
        System.out.println("Enter new username");
        return input.nextLine();
    }

    @Override
    public String typePassword() {

        System.out.println("Enter password");
        return input.nextLine();
    }

    @Override
    public String typeNewPassword() {

        System.out.println("Enter new password");
        return input.nextLine();
    }

    @Override
    public String typeActivityName() {

        System.out.println("Enter activity name: ");
        return input.nextLine();
    }

    @Override
    public String typeToStart() {

        System.out.println("Type anything to start!");
        return input.nextLine();
    }

    @Override
        public int choice() {
            int choice = input.nextInt();
            return choice;
        }



}
