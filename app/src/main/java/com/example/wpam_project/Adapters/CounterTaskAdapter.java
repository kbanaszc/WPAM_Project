package com.example.wpam_project.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wpam_project.DBHelpers.CounterTaskDBHelper;
import com.example.wpam_project.DBHelpers.ToDoTaskDBHelper;
import com.example.wpam_project.R;
import com.example.wpam_project.TasksFragment;

import java.util.ArrayList;

public class CounterTaskAdapter extends RecyclerView.Adapter<CounterTaskAdapter.MyViewHolder> {

    private Context context;
    private ArrayList counter_id, counter_task,counter_goal,counter_to_goal,counter_adder,counter_done;
    Fragment fragment;
    int doneTasksCount = 0;
    TasksFragment fragmentParent;


    public CounterTaskAdapter(TasksFragment fragmentParent,Fragment fragment, Context context, ArrayList counter_id, ArrayList counter_task, ArrayList counter_goal,
                              ArrayList counter_to_goal, ArrayList counter_adder, ArrayList counter_done){
        this.fragment = fragment;
        this.context = context;
        this.counter_id = counter_id;
        this.counter_task = counter_task;
        this.counter_goal = counter_goal;
        this.counter_to_goal = counter_to_goal;
        this.counter_adder = counter_adder;
        this.counter_done = counter_done;
        this.fragmentParent = fragmentParent;
    }

    @NonNull
    @Override
    public CounterTaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_counter_task,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CounterTaskAdapter.MyViewHolder holder, int position) {
        String plasterwork = String.valueOf(counter_done.get(position));
        holder.counter_text.setText(String.valueOf(counter_task.get(position)));
        holder.counter_goal.setText(String.valueOf(counter_goal.get(position)));
        holder.counter_to_goal.setText(String.valueOf(counter_to_goal.get(position)));

        if (plasterwork.equals("1")) {
            holder.counter_text.setTextColor(context.getResources().getColor(R.color.teal_200));
            holder.adder_button.setText("DONE");
        } else {
            holder.adder_button.setText("+"+String.valueOf(counter_adder.get(position)));
            holder.counter_text.setTextColor(context.getResources().getColor(R.color.teal_700));
        holder.adder_button.setOnClickListener(v->{
            int a = Integer.parseInt(String.valueOf(counter_goal.get(position)));
            int b = Integer.parseInt(String.valueOf(counter_to_goal.get(position)));
            if(a>b){
                b=b+Integer.parseInt(String.valueOf(counter_adder.get(position)));
                boolean x = Boolean.parseBoolean(String.valueOf(counter_done.get(position)));
                if(a<=b){x = true; doneTasksCount++;
                    Toast.makeText(context, String.valueOf(doneTasksCount), Toast.LENGTH_SHORT).show();}
                CounterTaskDBHelper counterTaskDBHelper = new CounterTaskDBHelper(context);
                counterTaskDBHelper.appendMethod(String.valueOf(counter_id.get(position)),
                        String.valueOf(counter_task.get(position)),
                        Integer.parseInt(String.valueOf(counter_goal.get(position))),
                        b,
                        Integer.parseInt(String.valueOf(counter_adder.get(position))),
                        x);
                fragmentParent.navigateToTasksView();

            }
        });
        }
        holder.mainLayoutCounter.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete");
            builder.setMessage("Are you sure you want to delete the task ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    CounterTaskDBHelper counterTaskDBHelper = new CounterTaskDBHelper(context);
                    counterTaskDBHelper.deleteOneRow(String.valueOf(counter_id.get(position)));
                    counter_id.remove(position);
                    notifyItemRangeChanged(position, getItemCount());
                    notifyItemRemoved(position);

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return counter_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView counter_text, counter_goal, counter_to_goal;
        Button adder_button;
        LinearLayout mainLayoutCounter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            counter_text = itemView.findViewById(R.id.counter_text);
            counter_goal = itemView.findViewById(R.id.goal_text);
            counter_to_goal = itemView.findViewById(R.id.to_goal_text);
            adder_button = itemView.findViewById(R.id.adder_button);
            mainLayoutCounter = itemView.findViewById(R.id.mainLayoutCounter);
        }
    }

    public void clearRecyclerView(){
        for(int i = 0; i<getItemCount();i++) {
            counter_id.clear();
            notifyItemRangeChanged(0, getItemCount());
        }
    }
}
