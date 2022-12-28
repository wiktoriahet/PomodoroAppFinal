package com.pomodoro;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class User extends Activity implements IUser {

    Scanner input = new Scanner(System.in);
    Date date = new Date();

    WritingService writingService = new WritingService();
    Password passwordC = new Password();

    String username;
    String password;

    @Override
    public boolean logIn() {

        System.out.println("Enter username");
        username = input.nextLine();
        System.out.println("Enter password");
        password = input.nextLine();

        if (passwordC.isPasswordCorrect(username, password) != false) {
            //zaloguj
            System.out.println("logging");//temp.
            return true;
        } else {
            System.out.println("Wrong username or password");
            return false;
        }
    }

    @Override
    public void createNewAccount() {

        try {

            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();

            username = writingService.typeUserName();

            if (correctUsername(username)) {
                password = writingService.typePassword();
                statement.executeUpdate("INSERT INTO user " + "VALUES('" + username + "', '" + password + "', '" + date + "')");

            } else {
                System.out.println("User with this username already exists. ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void continueAsGuest() {

        writingService.guestMenuMsg();

        int choice = writingService.choice();

        switch (choice) {
            case 1 -> defaultSession();
            case 2 -> {
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
            }
        }
    }

    @Override
    public boolean correctUsername(String username) {
        try {

            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSetName = statement.executeQuery("select * from user");

            while (resultSetName.next()) {
                if (resultSetName.getString("id_username").equalsIgnoreCase(username)) {
                    return false;
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void userSettings(String username) {

        writingService.userSettingsMsg();

        int choice = writingService.choice();

        switch (choice) {
            case 1 -> passwordC.changePassword(username);
            case 2 -> changeUsername();
        }
    }


    private void changeUsername() {

        System.out.println("Enter password");
        password = input.nextLine();
        if (passwordC.isPasswordCorrect(username, password)) {

            try {

                Connection connection = DriverManager.getConnection("Here you write your sql url");
                Statement statement = connection.createStatement();
                ResultSet resultSetName = statement.executeQuery("select * from user");
                PreparedStatement pstmt = connection.prepareStatement("update user set id_username = ? where password = ?;");

                while (resultSetName.next()) {
                    if (resultSetName.getString("id_username").equalsIgnoreCase(username)) {


                        System.out.println("Enter new username");
                        username = input.nextLine();

                        if (correctUsername(username)) {

                            pstmt.setString(1, username);
                            pstmt.setString(2, password);

                            pstmt.executeUpdate();


                        } else System.out.println("Username already exists");
                    }
                }

            } catch (
                    Exception e) {
                e.printStackTrace();
            }

        } else System.out.println("Incorrect password");

    }
}
