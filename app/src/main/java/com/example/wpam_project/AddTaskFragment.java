package com.example.wpam_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.ActivityKt;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class AddTaskFragment extends Fragment {

    private Button todoButtonAdd, countingButtonAdd, timeButtonAdd;
    private ActivityKt NavigationHostFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Add Task");
        View rootView = inflater.inflate(R.layout.fragment_add_task, container, false);
        todoButtonAdd = rootView.findViewById(R.id.add_todo_task_button);
        countingButtonAdd= rootView.findViewById(R.id.add_counting_task_button);
        timeButtonAdd = rootView.findViewById(R.id.add_time_task_button);

        todoButtonAdd.setOnClickListener(v->navigateToAddToDoTask());
        countingButtonAdd.setOnClickListener(v -> navigateToAddCountingTask());
        timeButtonAdd.setOnClickListener(v->navigateToAddTimeTask());


        // Inflate the layout for this fragment
        return rootView;
    }

    public void navigateToAddToDoTask(){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new AddToDoTaskFragment()).commit();
    }
    public void navigateToAddTimeTask(){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new AddTimeTaskFragment()).commit();
    }
    public void navigateToAddCountingTask(){
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, new AddCountingTaskFragment()).commit();
    }
}