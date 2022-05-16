package com.example.wpam_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TimeTaskFragment extends Fragment {

    Button timer_button;
    TextView counter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Tasks");
        View rootView = inflater.inflate(R.layout.fragment_time_task_view, container, false);
        timer_button = rootView.findViewById(R.id.time_start_button);
        counter = rootView.findViewById(R.id.timer_time_text);


        return rootView;
    }
}