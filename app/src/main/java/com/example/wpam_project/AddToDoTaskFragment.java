package com.example.wpam_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddToDoTaskFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Task");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_to_do_task, container, false);
    }
}