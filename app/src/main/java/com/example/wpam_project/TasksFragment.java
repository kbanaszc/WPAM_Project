package com.example.wpam_project;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wpam_project.Adapters.TimeTaskAdapter;
import com.example.wpam_project.Adapters.ToDoTaskAdapter;
import com.example.wpam_project.DBHelpers.TimeTaskDBHelper;
import com.example.wpam_project.DBHelpers.ToDoTaskDBHelper;

import java.util.ArrayList;


public class TasksFragment extends Fragment {

    RecyclerView recyclerViewToDo, recyclerViewTime;

    TimeTaskDBHelper timeTaskDBHelper;
    ToDoTaskDBHelper toDoTaskDBHelper;

    ArrayList<String> todo_id, todo_task, todo_done;
    ArrayList<String> time_id, time_task,time_time, time_done;

    ToDoTaskAdapter toDoTaskAdapter;
    TimeTaskAdapter timeTaskAdapter;

    Context context;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    getActivity().setTitle("Your tasks");
    View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);
    recyclerViewToDo = rootView.findViewById(R.id.recyclerView);
    recyclerViewTime = rootView.findViewById(R.id.recyclerViewTime);
    this.context = container.getContext();
    this.fragment = getParentFragment();

    toDoTaskDBHelper = new ToDoTaskDBHelper(getActivity());
    timeTaskDBHelper = new TimeTaskDBHelper(getActivity());
    todo_id = new ArrayList<>();
    todo_task = new ArrayList<>();
    todo_done = new ArrayList<>();
    time_id = new ArrayList<>();
    time_task = new ArrayList<>();
    time_time = new ArrayList<>();
    time_done = new ArrayList<>();

    storeDataInArraysToDo();
        toDoTaskAdapter = new ToDoTaskAdapter(fragment,context,todo_id,todo_task,todo_done);
        recyclerViewToDo.setAdapter(toDoTaskAdapter);
        recyclerViewToDo.setLayoutManager(new LinearLayoutManager(getActivity()));

    storeDataInArraysTime();
        timeTaskAdapter = new TimeTaskAdapter(fragment,context,time_id,time_task,time_time,time_done);
        recyclerViewTime.setAdapter(timeTaskAdapter);
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
}

    void storeDataInArraysToDo(){
        Cursor cursor = toDoTaskDBHelper.readAllData();
        if(cursor.getCount()==0){
        }else{
            while(cursor.moveToNext()){
                todo_id.add(cursor.getString(0));
                todo_task.add(cursor.getString(1));
                todo_done.add(cursor.getString(2));
            }
        }
    }

    void storeDataInArraysTime(){
        Cursor cursor = timeTaskDBHelper.readAllData();
        if(cursor.getCount()==0){
        }else{
            while(cursor.moveToNext()){
                time_id.add(cursor.getString(0));
                time_task.add(cursor.getString(1));
                time_time.add(cursor.getString(2));
                time_done.add(cursor.getString(3));
            }
        }
    }



}