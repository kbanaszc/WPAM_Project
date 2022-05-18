package com.example.wpam_project.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wpam_project.DBHelpers.TimeTaskDBHelper;
import com.example.wpam_project.R;
import com.example.wpam_project.TasksFragment;

import java.util.ArrayList;

public class TimeTaskAdapter extends RecyclerView.Adapter<TimeTaskAdapter.MyViewHolder> {

    private Context context;
    private ArrayList time_id, time_task, time_time, time_done;
    private Fragment fragment;
    private TasksFragment fragmentParent;

    public TimeTaskAdapter(TasksFragment fragmentParent,Fragment fragment, Context context, ArrayList time_id, ArrayList time_task, ArrayList time_time, ArrayList time_done){
        this.fragment = fragment;
        this.context = context;
        this.time_id = time_id;
        this.time_task = time_task;
        this.time_time = time_time;
        this.time_done = time_done;
        this.fragmentParent = fragmentParent;
    }

    @NonNull
    @Override
    public TimeTaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_time_task,parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String plasterwork = String.valueOf(time_done.get(position));
        holder.time_text_input.setText(String.valueOf(time_task.get(position)));
        holder.time_time_input.setText(String.valueOf(time_time.get(position))+":00");
        if (plasterwork.equals("1")) {
            holder.time_text_input.setTextColor(context.getResources().getColor(R.color.teal_200));
            holder.time_time_input.setTextColor(context.getResources().getColor(R.color.teal_200));
        } else {
            holder.time_text_input.setTextColor(context.getResources().getColor(R.color.teal_700));
            holder.time_time_input.setTextColor(context.getResources().getColor(R.color.teal_700));
        }
        holder.mainLayoutTime.setOnClickListener(v -> {
                fragmentParent.navigateToTimeView(Integer.parseInt(String.valueOf(time_time.get(position))), String.valueOf(time_id.get(position)));

                TimeTaskDBHelper timeTaskDBHelper = new TimeTaskDBHelper(context);
                timeTaskDBHelper.taskChecked(String.valueOf(time_id.get(position)),
                        String.valueOf(time_task.get(position)),
                        Integer.parseInt(String.valueOf(time_time.get(position))),
                        true);
        });

        holder.mainLayoutTime.setOnLongClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Delete");
            builder.setMessage("Are you sure you want to delete the task ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TimeTaskDBHelper timeTaskDBHelper = new TimeTaskDBHelper(context);
                    timeTaskDBHelper.deleteOneRow(String.valueOf(time_id.get(position)));
                    time_id.remove(position);
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
        fragmentParent.ProgressCounter();
    }

    @Override
    public int getItemCount() {
        return time_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView time_text_input, time_time_input;
        LinearLayout mainLayoutTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time_text_input = itemView.findViewById(R.id.time_task_name);
            time_time_input = itemView.findViewById(R.id.time_to_count);
            mainLayoutTime = itemView.findViewById(R.id.mainLayoutTime);
        }
    }

}
