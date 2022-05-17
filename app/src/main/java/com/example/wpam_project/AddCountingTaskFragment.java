package com.example.wpam_project;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wpam_project.DBHelpers.CounterTaskDBHelper;
import com.example.wpam_project.DBHelpers.TimeTaskDBHelper;

public class AddCountingTaskFragment extends Fragment {
    EditText taskName, goal, adder;
    Button saveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Time Task");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_counting_task, container, false);
        taskName = rootView.findViewById(R.id.counting_name_input);
        goal = rootView.findViewById(R.id.counting_task_goal);
        adder = rootView.findViewById(R.id.counting_task_append);
        saveButton = rootView.findViewById(R.id.counting_save_button);

        saveButton.setOnClickListener(v -> {
            int x = Integer.parseInt(goal.getText().toString().trim());
            int y = Integer.parseInt(adder.getText().toString().trim());
            if (x % y == 0) {
                CounterTaskDBHelper counterTaskDBHelper = new CounterTaskDBHelper(getContext());
                counterTaskDBHelper.addTask(taskName.getText().toString().trim(),
                        x,
                        0,
                        y,
                        false);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Error");
                builder.setMessage("Your Goal must be a multiplication of your adder!");
                builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
            }
        });
        return rootView;
    }
}