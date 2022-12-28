package com.pomodoro;

import java.sql.*;

public class Password implements IPassword{

    WritingService writingService = new WritingService();

    @Override
    public boolean isPasswordCorrect(String userName, String password) {
        try {
            Connection connection = DriverManager.getConnection("Here you write your sql url");
            Statement statement = connection.createStatement();
            ResultSet resultSetPassword = statement.executeQuery("select * from user");

            while (resultSetPassword.next()) {
                if (resultSetPassword.getString("id_username").equalsIgnoreCase(userName)) {
                    return resultSetPassword.getString("password").equals(password);
                }
            }

        } catch (
                Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public void changePassword(String username) {

        String password = writingService.typePassword();
        if(isPasswordCorrect(username, password)){

            try {

                Connection connection = DriverManager.getConnection("Here you write your sql url");
                Statement statement = connection.createStatement();
                ResultSet resultSetName = statement.executeQuery("select * from user");
                PreparedStatement pstmt = connection.prepareStatement("update user set password = ? where id_username = ?;");

                while (resultSetName.next()) {
                    if (resultSetName.getString("id_username").equalsIgnoreCase(username)) {


                        String nPassword = writingService.typeNewPassword();

                        pstmt.setString(1, nPassword);
                        pstmt.setString(2, username);

                        pstmt.executeUpdate();

                    }
                }


            } catch (
                    Exception e) {
                e.printStackTrace();
            }


        } else System.out.println("Incorrect password");


    }
}
