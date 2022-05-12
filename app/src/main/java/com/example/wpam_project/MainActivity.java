package com.example.wpam_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ActionBar actionBar;

    TasksFragment tasksFragment = new TasksFragment();
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    CalendarFragment calendarFragment = new CalendarFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,tasksFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.tasksFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,tasksFragment).commit();
                        return true;
                    case R.id.addTaskFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,addTaskFragment).commit();
                        return true;
                    case R.id.calendarFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, calendarFragment).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });

    }
}