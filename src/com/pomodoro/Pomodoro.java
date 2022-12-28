package com.pomodoro;

import java.util.Collections;

public class Pomodoro implements IPomodoro{

    WritingService writingService = new WritingService();

    @Override
    public void informationAboutPomodoro() {

        System.out.println("The Pomodoro Technique is a time management method developed by Francesco Cirillo in the late 1980s.");
        System.out.println("It uses a kitchen timer to break work into intervals, typically 25 minutes in length, separated by short breaks.");
        System.out.println("Each interval is known as a pomodoro, from the Italian word for tomato, after the tomato-shaped kitchen timer Cirillo used as a university student.");
        System.out.println("In this app you can create any activity and use the pomodoro technique to achieve your goals.");
        System.out.println("You can either use the default pomodoro settings, which is 25 minutes of work separated by short (5 minutes) and long (10 minutes) breaks");
        System.out.println("or create your own custom timer.");
        System.out.println("By creating activity you have an access to the statistics.");
        System.out.println("It can help you with tracking your progress with the activity and seeing how much time you have actually spent on it.");
        System.out.println("If you don't want to create an account you can continue as a guest.");
        System.out.println("Guest users can use both default and custom pomodoro timers.");
        System.out.println("Only users with an account have an access to the statistics.");
        System.out.println("You can create an account and save your data after finished activity while using the app as a guest.");
        System.out.println("You can delete your account and all the data at any point.");

    }

    @Override
    public void defaultSession() {

        long workTime = 25;
        long shortBreakTime = 5;
        int sessionCount = 5;
        session(sessionCount, workTime, shortBreakTime);

    }


    @Override
    public void session(int sessionCount, long workTime, long shortBreakTime, long longBreakTime) {
        //cos tu chujnia, do poprawki

        int counter = sessionCount;
        while (counter > 0) {

            writingService.newPomodoroSession();
            writingService.newWorkSession();
            String toStart = writingService.typeToStart();
            startingAfterTyping(toStart, workTime);
            writingService.newBreakSession();
            toStart = writingService.typeToStart();
            if (counter % 2 == 0) {
                startingAfterTyping(toStart, longBreakTime);
            } else {
                startingAfterTyping(toStart, shortBreakTime);
            }

            counter--;

        }

    }

    @Override
    public void session(int sessionCount, long workTime, long shortBreakTime) {

        int counter = sessionCount;
        while (counter > 0) {

            writingService.newPomodoroSession();
            writingService.newWorkSession();
            String toStart = writingService.typeToStart();
            startingAfterTyping(toStart, workTime);
            writingService.newBreakSession();
            toStart = writingService.typeToStart();
            startingAfterTyping(toStart, shortBreakTime);
            counter--;

        }


    }

    @Override
    public void pomodoroTimer(long total) {

        long nTotal = total * 60;

        for (int i = 1; i <= nTotal; i++) {
            try {
                Thread.sleep(1000);
                printProgress(nTotal, i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long checkLongBreaksAmount(int sessionCount) {
        long counter = 0;
        for (int i = 1; i < sessionCount; i++) {
            if (i % 2 == 0) {
                counter++;
            }
        }
        return counter;
    }


    public static void printProgress(long total, long current) {

        long currentTime = current;

        long hours = total / 60 / 60;
        long minutes = total / 60;
        long seconds = total % 60;

        if (minutes > 59) {
            minutes = 0;
        }

        if (seconds > 59) {
            seconds = 0;
        }

        long activityTime = currentTime;
        long currentHours = activityTime / 60 / 60;
        long currentMinutes = activityTime / 60;
        long currentSeconds = activityTime % 60;

        if (currentMinutes > 59) {
            currentMinutes = 0;
        }
        if (currentSeconds > 59) {
            currentSeconds = 0;
        }

        StringBuilder string = new StringBuilder(140);
        int percent = (int) (current * 100 / total);
        string
                .append('\r')

                .append(String.join("",
                        Collections.nCopies(percent == 0 ? 2 : 2 - (int)
                                (Math.log10(percent)), " ")))

                .append(String.format(" %d%% [",
                        percent))

                .append(String.join("",
                        Collections.nCopies(percent, "=")))

                .append('>')

                .append(String.join("",
                        Collections.nCopies(100 - percent, " ")))

                .append(']')

                .append(String.format(" %s:%s:%s/%s:%s:%s",
                        zeroAdder(currentHours),
                        zeroAdder(currentMinutes),
                        zeroAdder(currentSeconds),
                        zeroAdder(hours),
                        zeroAdder(minutes),
                        zeroAdder(seconds)));

        System.out.print(string);

    }

    private static String zeroAdder(long time) {
        if (time < 10) {
            return "0" + time;
        } else return "" + time;
    }

    private void startingAfterTyping(String type, long time) {

        if (!type.isEmpty()) {
            pomodoroTimer(time);
        } //needs change!!
    }


}
