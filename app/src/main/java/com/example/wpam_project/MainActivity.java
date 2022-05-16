package com.example.wpam_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.security.Permissions;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    TasksFragment tasksFragment = new TasksFragment();
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    CalendarFragment calendarFragment = new CalendarFragment();
    private ActivityKt NavigationHostFragment;


    @NonNull
    @Override
    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,tasksFragment).commit();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();}


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.tasksFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,tasksFragment).commit();
                        return true;
                    case R.id.addTaskFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,addTaskFragment).commit();
                        return true;
                    case R.id.calendarFragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, calendarFragment).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });



    }




}