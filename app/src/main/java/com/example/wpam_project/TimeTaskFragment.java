package com.example.wpam_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class TimeTaskFragment extends Fragment {

    Button timer_button;
    TextView timer_text;
    Timer timer;
    boolean timerStarted = false;
    long time;
    String mainTaskId;
    TasksFragment tasksFragment;


    public TimeTaskFragment(long time, String id) {
        this.time = time;
        this.mainTaskId = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Tasks");
        View rootView = inflater.inflate(R.layout.fragment_time_task_view, container, false);
        timer_button = rootView.findViewById(R.id.time_start_button);
        timer_text = rootView.findViewById(R.id.timer_time_text);
        timer = new Timer();
        timer_text.setText(formatTime((int) 00,(int)Math.round(time)));
        timer_button.setOnClickListener(v->{
            if(!timerStarted){
                timerStarted=true;
                startTimer();
            }
        });
        return rootView;
    }

    private void startTimer() {
        long theTime = time * 60 * 1000;
        new CountDownTimer(theTime, 1000) {

            public void onTick(long millisUntilFinished) {
                timer_text.setText(getTimerText(millisUntilFinished));
            }

            public void onFinish() {
                timer_text.setText("done!");
                timer_button.setText("RETURN TO TASKS");
                timer_button.setOnClickListener(v->navigateToTasksView());
            }
        }.start();
    }
    @NonNull
    private String getTimerText(long time) {
        int rounded = (int) Math.round(time/1000);
        int seconds = (rounded % 60);
        int minutes = (rounded / 60) % 60 ;

        return formatTime(seconds, minutes);
    }
    @NonNull
    private String formatTime(int seconds, int minutes){
        return String.format("%02d",minutes) + " : "+String.format("%02d",seconds);
    }
    public void navigateToTasksView(){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new TasksFragment()).commit();
    }

}