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

public class AddToDoTaskFragment extends Fragment {

    EditText taskNameText;
    Button saveButton, attachButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Add ToDo Task");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_to_do_task, container, false);
        taskNameText = rootView.findViewById(R.id.todo_name_input);
        saveButton = rootView.findViewById(R.id.todo_save_button);
        attachButton = rootView.findViewById(R.id.attach_app_button);

        saveButton.setOnClickListener(v -> {
            ToDoTaskDBHelper toDoTaskDBHelper = new ToDoTaskDBHelper(getContext());
            toDoTaskDBHelper.addTask(taskNameText.getText().toString().trim(), false);
        });
        return rootView;
    }
}
