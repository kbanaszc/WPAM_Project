package com.example.wpam_project;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarFragment extends Fragment {

    TextView textView;
    TasksFragment tasksFragment;
    double progress;

    CalendarFragment(TasksFragment tasksFragment){
        this.tasksFragment = tasksFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Today progress");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_calendar, container, false);
        textView = rootView.findViewById(R.id.progress_percentage);
        progress = tasksFragment.ProgressCounter();
        progress = Math.round(progress*100.0);
        textView.setTextColor(ContextCompat.getColor(getContext(), R.color.bad_progress));
        if(progress>=40){
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.medium_progress));
        }
        if(progress>=70){
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.good_progress));
        }
        if(progress==100){
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.exelent_progress));
        }
        String prog = String.valueOf(progress);
        textView.setText(prog+"%");

        return rootView;
    }
}