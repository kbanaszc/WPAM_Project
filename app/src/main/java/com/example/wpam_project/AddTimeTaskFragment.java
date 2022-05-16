package com.example.wpam_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.wpam_project.DBHelpers.TimeTaskDBHelper;
import com.example.wpam_project.DBHelpers.ToDoTaskDBHelper;

public class AddTimeTaskFragment extends Fragment {
    EditText taskName, timeToCount;
    Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Time Task");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_time_task, container, false);
        taskName = rootView.findViewById(R.id.time_name_input);
        timeToCount = rootView.findViewById(R.id.time_to_countdown);
        saveButton = rootView.findViewById(R.id.time_save_button);

        saveButton.setOnClickListener(v -> {
            TimeTaskDBHelper timeTaskDBHelper = new TimeTaskDBHelper(getContext());
            timeTaskDBHelper.addTask(taskName.getText().toString().trim(), Integer.parseInt(timeToCount.getText().toString().trim()),false);
        });

        return rootView;
    }
}