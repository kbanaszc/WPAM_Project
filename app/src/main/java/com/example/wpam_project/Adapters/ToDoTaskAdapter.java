package com.example.wpam_project.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wpam_project.DBHelpers.ToDoTaskDBHelper;
import com.example.wpam_project.R;

import java.util.ArrayList;

public class ToDoTaskAdapter extends RecyclerView.Adapter<ToDoTaskAdapter.MyViewHolder> {

    private Context context;
    private ArrayList todo_id, todo_task, todo_done;
    Fragment fragment;


    public ToDoTaskAdapter(Fragment fragment, Context context, ArrayList todo_id, ArrayList todo_task, ArrayList todo_done){
        this.fragment = fragment;
        this.context = context;
        this.todo_id = todo_id;
        this.todo_task = todo_task;
        this.todo_done = todo_done;
    }

    @NonNull
    @Override
    public ToDoTaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_todo_task,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoTaskAdapter.MyViewHolder holder, int position) {
        String plasterwork = String.valueOf(todo_done.get(position));

        holder.todo_checkbox.setChecked(plasterwork.equals("1"));
        holder.todo_text.setText(String.valueOf(todo_task.get(position)));
        if(plasterwork.equals("1")){
            holder.todo_text.setTextColor(context.getResources().getColor(R.color.teal_200));
            holder.todo_text.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else{
            holder.todo_text.setTextColor(context.getResources().getColor(R.color.teal_700));
            holder.todo_text.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
        }

        holder.todo_checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                holder.todo_text.setTextColor(context.getResources().getColor(R.color.teal_200));
                holder.todo_text.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            }
            else{
                holder.todo_text.setTextColor(context.getResources().getColor(R.color.teal_700));
                holder.todo_text.setPaintFlags(Paint.ANTI_ALIAS_FLAG);
            }
            ToDoTaskDBHelper toDoTaskDBHelper = new ToDoTaskDBHelper(context);
            toDoTaskDBHelper.taskChecked(String.valueOf(todo_id.get(position)),
                    String.valueOf(todo_task.get(position)),
                    isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return todo_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView todo_text;
        CheckBox todo_checkbox;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            todo_text = itemView.findViewById(R.id.todo_text);
            todo_checkbox = itemView.findViewById(R.id.todo_checkbox);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
