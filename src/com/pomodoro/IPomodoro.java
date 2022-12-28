package com.pomodoro;

public interface IPomodoro {

    void informationAboutPomodoro();
    void defaultSession();
    void session(int sessionCount, long workTime, long shortBreakTime, long longBreakTime);
    void session(int sessionCount, long workTime, long shortBreakTime);
    void pomodoroTimer(long total);

    long checkLongBreaksAmount(int sessionCount);


}
