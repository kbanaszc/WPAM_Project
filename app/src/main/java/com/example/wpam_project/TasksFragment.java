package com.example.wpam_project;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wpam_project.Adapters.CounterTaskAdapter;
import com.example.wpam_project.Adapters.TimeTaskAdapter;
import com.example.wpam_project.Adapters.ToDoTaskAdapter;
import com.example.wpam_project.DBHelpers.CounterTaskDBHelper;
import com.example.wpam_project.DBHelpers.TimeTaskDBHelper;
import com.example.wpam_project.DBHelpers.ToDoTaskDBHelper;

import java.util.ArrayList;


public class TasksFragment extends Fragment {

    RecyclerView recyclerViewToDo, recyclerViewTime, recyclerViewCounter;

    TimeTaskDBHelper timeTaskDBHelper;
    ToDoTaskDBHelper toDoTaskDBHelper;
    CounterTaskDBHelper counterTaskDBHelper;

    ArrayList<String> todo_id, todo_task, todo_done;
    ArrayList<String> time_id, time_task,time_time, time_done;
    ArrayList<String> counter_id, counter_task, counter_goal, counter_to_goal, counter_adder, counter_done;
    public ArrayList<String> timer_tasks_done;

    ToDoTaskAdapter toDoTaskAdapter;
    TimeTaskAdapter timeTaskAdapter;
    CounterTaskAdapter counterTaskAdapter;

    Context context;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    getActivity().setTitle("Your tasks");
    View rootView = inflater.inflate(R.layout.fragment_tasks, container, false);
    recyclerViewToDo = rootView.findViewById(R.id.recyclerView);
    recyclerViewTime = rootView.findViewById(R.id.recyclerViewTime);
    recyclerViewCounter = rootView.findViewById(R.id.recyclerViewCount);
    this.context = container.getContext();
    this.fragment = getParentFragment();

    ArrayList<String> timerTaskDone = new ArrayList<>();
    timerTaskDone.add("dupa");

    toDoTaskDBHelper = new ToDoTaskDBHelper(getActivity());
    timeTaskDBHelper = new TimeTaskDBHelper(getActivity());
    counterTaskDBHelper = new CounterTaskDBHelper(getActivity());
    todo_id = new ArrayList<>();
    todo_task = new ArrayList<>();
    todo_done = new ArrayList<>();
    time_id = new ArrayList<>();
    time_task = new ArrayList<>();
    time_time = new ArrayList<>();
    time_done = new ArrayList<>();
    counter_id = new ArrayList<>();
    counter_task = new ArrayList<>();
    counter_goal = new ArrayList<>();
    counter_to_goal = new ArrayList<>();
    counter_adder = new ArrayList<>();
    counter_done = new ArrayList<>();


    storeDataInArraysToDo();
        toDoTaskAdapter = new ToDoTaskAdapter(this,fragment,context,todo_id,todo_task,todo_done);
        recyclerViewToDo.setAdapter(toDoTaskAdapter);
        recyclerViewToDo.setLayoutManager(new LinearLayoutManager(getActivity()));


    storeDataInArraysTime();
        timeTaskAdapter = new TimeTaskAdapter(this, fragment,context,time_id,time_task,time_time,time_done);
        recyclerViewTime.setAdapter(timeTaskAdapter);
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(getActivity()));

        storeDataInArraysCounter();
        counterTaskAdapter = new CounterTaskAdapter(this,fragment,context,counter_id,counter_task,counter_goal,counter_to_goal,counter_adder,counter_done);
        recyclerViewCounter.setAdapter(counterTaskAdapter);
        recyclerViewCounter.setLayoutManager(new LinearLayoutManager(getActivity()));

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
    void storeDataInArraysCounter(){
        Cursor cursor = counterTaskDBHelper.readAllData();
        if(cursor.getCount()==0){
        }else{
            while(cursor.moveToNext()){
                counter_id.add(cursor.getString(0));
                counter_task.add(cursor.getString(1));
                counter_goal.add(cursor.getString(2));
                counter_to_goal.add(cursor.getString(3));
                counter_adder.add(cursor.getString(4));
                counter_done.add(cursor.getString(5));
            }
        }
    }

    public void navigateToTimeView(long time_from_holder, String id){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new TimeTaskFragment(time_from_holder,id)).commit();
    }
    public void navigateToTasksView(){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new TasksFragment()).commit();
    }
}