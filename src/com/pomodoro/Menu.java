package com.pomodoro;

import java.util.Date;
import java.util.Scanner;

public class Menu extends User implements IMenu{

    WritingService writingService = new WritingService();

    @Override
    public void startMenu() {

        writingService.menuMessage();

        int choice = writingService.choice();


        switch (choice){

            case 1 -> {
                if (logIn()) {
                    startUserMenu(username);
                }
            }
            case 2 -> createNewAccount();
            case 3 -> continueAsGuest();
            case 4 -> informationAboutPomodoro();
            case 5 -> exitProgram();

        }

    }

    @Override
    public void startUserMenu(String username) {

        writingService.userMenuMessage(username);

        int choice = writingService.choice();


        switch (choice){

            case 1 -> createNewActivity(username);
            case 2 -> {

                String activityName = chooseActivity(username);

                if(!activityName.isEmpty()){

                startActivityMenu(activityName);
            }
            }
            case 3 -> userStatistics(username);
            case 4 -> deleteActivity(username);
            case 5 -> userSettings(username);
            case 6 -> exitProgram();

        }
    }

    @Override
    public void startActivityMenu(String activityName) {
        Date date = new Date();

        System.out.println("ACTIVITY MENU FOR " + activityName);

        writingService.activityMenuMessage(activityName);

        int choice = writingService.choice();

        switch (choice){


            case 1 -> {
                defaultSession();
                saveStatistics(username, activityName, date);
            }
            case 2 ->{

                long workTime;
                long shortBreakTime;
                long longBreakTime;
                int sessionCount;


                System.out.println("Type number of sessions: ");
                sessionCount = writingService.choice();

                System.out.println("Type work time duration: ");
                workTime = writingService.choice();

                System.out.println("Type short break duration: ");
                shortBreakTime = writingService.choice();

                System.out.println("Type long break duration: ");
                longBreakTime = writingService.choice();

                session(sessionCount, workTime, shortBreakTime, longBreakTime);

                saveStatistics(username, activityName, sessionCount, workTime, shortBreakTime, longBreakTime, date);
                
            }

            case 3 -> exitProgram();

        }


    }

    @Override
    public void exitProgram() {

        System.out.println("GOOD BYE!");
        System.exit(0);

    }

}
