package com.pomodoro;

import java.util.Date;

public interface IActivity {

    void createNewActivity(String username);
    void userStatistics(String username);
    void deleteActivity(String username);
    void activityList(String username);
    void saveStatistics(String username, String activityName, int sessionCount, long workTime, long shortBreakTime, long longBreakTime, Date thisDate);
    void saveStatistics(String username, String activityName, Date thisDate);

    boolean activityExists(String username, String activityName);

    String chooseActivity(String username);

}
