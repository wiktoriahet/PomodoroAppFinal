package com.pomodoro;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity extends Pomodoro implements IActivity{

    WritingService writingService = new WritingService();

    @Override
    public void createNewActivity(String username) {

        String activityName = writingService.typeActivityName();

        if (activityExists(username, activityName)) {

            System.out.println("Activity already exists. ");

        } else {


            try {
                Connection connection = DriverManager.getConnection("Here you write your sql url");
                Statement statement = connection.createStatement();
                statement.executeUpdate(
                        "INSERT INTO activity "
                                + "VALUES(" +
                                "'" + username + "', " +
                                "'" + activityName +
                                "')");
                System.out.println("Created new activity " + activityName);

            } catch (
                    Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public String chooseActivity(String username) {

        activityList(username);
        String activityName = writingService.typeActivityName();
        if(activityExists(username, activityName)){
            return activityName;
        } else {
            System.out.println("Activity doesn't exist");
            return "";
        }
    }

    @Override
    public void userStatistics(String username) {

        List<String> activity = new ArrayList<>();
        int counter = 0;
        int counter2 = 0;
        int counter3=0;

        try {
            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activity");

            while (resultSet.next()) {

                if(resultSet.getString("id_username").equals(username)) {
                    activity.add(resultSet.getString("activity_name"));
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }

        List<Integer> numberOfSessions = new ArrayList<>();
        List<Integer> workTime = new ArrayList<>();
        List<Integer> breaksTime = new ArrayList<>();

        try{
            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement1 = connection.createStatement();


               for(int i = 0; i<activity.size(); i++){


                   ResultSet resultSet2 = statement1.executeQuery("select * from activity_data where activity_name = '" + activity.get(i) + "'");
                   while(resultSet2.next()){
                       if(resultSet2.getString("id_username").equals(username)){
                       counter+=resultSet2.getInt("number_of_sessions");
                       counter2+=resultSet2.getInt("work_time");
                       counter3+=resultSet2.getInt("breaks_time");

                       }

                   }
                   numberOfSessions.add(counter);
                   counter=0;
                   workTime.add(counter2);
                   counter2=0;
                   breaksTime.add(counter3);
                   counter3=0;


           }

        }catch (
                Exception e) {
            e.printStackTrace();
        }

        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%20s %20s %20s %20s", "ACTIVITY", "NUMBER OF SESSIONS", "WORK TIME", "BREAKS TIME");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for(int i = 0; i < activity.size(); i++)
        {
            System.out.format("%20s %20s %20s %20s", activity.get(i), numberOfSessions.get(i), workTime.get(i), breaksTime.get(i));
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------");

    }

    @Override
    public void deleteActivity(String username) {

        String activityName;
        activityName= writingService.typeActivityName();

        try {
            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activity");
            String query;
            query = "delete from activity where id_username = ? and activity_name = ? ";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            while (resultSet.next()) {


                    preparedStmt.setString(1, username);
                    preparedStmt.setString(2, activityName);
                    preparedStmt.execute();

            }
            System.out.println("Deleted " + activityName + " from users account.");

        } catch (
                Exception e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activity_data");
            String query;
            query = "delete from activity_data where id_username = ? and activity_name = ? ";
            PreparedStatement preparedStmt = connection.prepareStatement(query);

            while (resultSet.next()) {

                    preparedStmt.setString(1, username);
                    preparedStmt.setString(2, activityName);
                    preparedStmt.execute();

            }

            System.out.println("Deleted " + activityName + " from users account.");

        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void activityList(String username) {

        System.out.println("Available activities: ");

        try {
            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activity");

            int counter = 0;
            while (resultSet.next()) {

                if(resultSet.getString("id_username").equals(username)) {
                    counter++;
                    System.out.println(counter + ": " + resultSet.getString("activity_name"));
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveStatistics(String username, String activityName, int sessionCount, long workTime, long shortBreakTime, long longBreakTime, Date thisDate) {


        try {

            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();

            long longBreaksSum = checkLongBreaksAmount(sessionCount);
            long shortBreaksSum = sessionCount - longBreaksSum;
            long workTimeSum = workTime * sessionCount;
            long breaksTimeSum = longBreaksSum * longBreakTime + shortBreaksSum * shortBreakTime;


            statement.executeUpdate(

                    "INSERT INTO activity_data "
                            + "VALUES('"
                            + username +
                            "', '"
                            + activityName +
                            "', '"
                            + thisDate +
                            "', '"
                            + sessionCount +
                            "', '"
                            + workTimeSum +
                            "', '"
                            + breaksTimeSum +
                            "')");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void saveStatistics(String username, String activityName, Date thisDate) {

        try {

            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();

            long workTime = 25;
            long shortBreakTime = 5;
            int sessionCount = 5;


            long shortBreaksSum = sessionCount;
            long workTimeSum = workTime * sessionCount;
            long breaksTimeSum = shortBreaksSum * shortBreakTime;

            statement.executeUpdate(
                    "INSERT INTO activity_data "
                            + "VALUES('"
                            + username +
                            "', '"
                            + activityName +
                            "', '"
                            + thisDate +
                            "', '"
                            + sessionCount +
                            "', '"
                            + workTimeSum +
                            "', '"
                            + breaksTimeSum +
                            "')");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean activityExists(String username, String activityName) {

        try {

            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from activity");

            while (resultSet.next()) {

                if (resultSet.getString("id_username").equals(username) && resultSet.getString("activity_name").equals(activityName)) {

                        return true;

                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
