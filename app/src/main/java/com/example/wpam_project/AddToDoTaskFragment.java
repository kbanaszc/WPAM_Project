package com.example.wpam_project;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wpam_project.Adapters.ToDoTaskAdapter;
import com.example.wpam_project.DBHelpers.ToDoTaskDBHelper;

import java.util.ArrayList;

public class AddToDoTaskFragment extends Fragment {

    EditText taskNameText;
    Button saveButton, attachButton;
    ArrayList<String> todo_id, todo_task, todo_done;
    ToDoTaskDBHelper toDoDatabaseHelper;
    ToDoTaskAdapter todoAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("Add ToDo Task");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_to_do_task, container, false);

        taskNameText = rootView.findViewById(R.id.todo_name_input);
        saveButton = rootView.findViewById(R.id.todo_save_button);
        attachButton = rootView.findViewById(R.id.attach_app_button);

        saveButton.setOnClickListener(v->{
            addTaskMethod();
        });

        toDoDatabaseHelper = new ToDoTaskDBHelper(getContext());
        todo_id = new ArrayList<>();
        todo_task = new ArrayList<>();
        todo_done = new ArrayList<>();

        storeDataInArrays();

        todoAdapter = new ToDoTaskAdapter(this,getContext(),todo_id, todo_task,todo_done);
        recyclerView.setAdapter(todoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return rootView;
    }
    private void addTaskMethod() {
        String inputText = taskNameText.getText().toString().trim();
        if(inputText.isEmpty()){
            Toast.makeText(getContext(), "Enter some task", Toast.LENGTH_SHORT).show();
        }
        else{
            ToDoTaskDBHelper myDatabaseHelper = new ToDoTaskDBHelper(getContext());
            myDatabaseHelper.addTask(inputText, false);
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.addTaskFragment, new TasksFragment()).commit();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = ToDoTaskDBHelper.readAllData();
        if(cursor.getCount()==0){
        }else{
            while(cursor.moveToNext()){
                todo_id.add(cursor.getString(0));
                todo_task.add(cursor.getString(1));
                todo_done.add(cursor.getString(2));
            }
        }
    }
}