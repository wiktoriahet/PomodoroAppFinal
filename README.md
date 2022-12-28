# PomodoroAppFinal
An app to manage your productiviy during smaller and bigger projects and tasks.


This is my first Java aplication.
My goal was to learn basics of Java programming by making something I would like to use myself. 



For this app to work properly you need to make SQL schema (I used MySQL Workbench) with 3 tables inside and connect it with this code 
using your schema url and password in places I marked in the code. 

Your 3 tables should be called "activity", "activity_data" and "user" (or anything else if you change it in the code).

In "activity" you should make 2 columns - "id_username" and "activity_name".
Both columns should be selected as Primary Key with datatype varchar(45).

In "activity_data" you should make 6 columns - "id_username" (PK, varchar(45)), "activity_name" (PK, varchar(45)), "date" (PK, varchar(45)), 
"number_of_sessions" (varchar(45)), "work_time" (varchar(45)), "breaks_time" (varchar(45)).

In "user" you should make 3 columns - "id_username" (PK, varchar(45)), "password" (varchar(45)), "date_of_join" (varchar(45)).

Data from the app will be saved in this tables. 

App is not working continuously - you need to start it again after some of the process' finished.
