package com.example.wpam_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TodoTaskFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Tasks");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_task, container, false);
    }
}