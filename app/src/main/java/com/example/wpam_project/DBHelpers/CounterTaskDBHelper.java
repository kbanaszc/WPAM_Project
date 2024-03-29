package com.example.wpam_project.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CounterTaskDBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "CounterTask.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "counter_tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK_NAME = "task_name";
    private static final String COLUMN_GOAL = "goal";
    private static final String COLUMN_TO_GOAL = "to_goal";
    private static final String COLUMN_ADDER = "adder";
    private static final String COLUMN_DONE = "task_done";


    public CounterTaskDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TASK_NAME + " TEXT, " +
                        COLUMN_GOAL + " INTEGER, " +
                        COLUMN_TO_GOAL + " INTEGER, " +
                        COLUMN_ADDER + " INTEGER, " +
                        COLUMN_DONE + " BOOLEAN);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTask(String task,int goal, int toGoal, int adder, boolean done){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK_NAME, task);
        cv.put(COLUMN_GOAL, goal);
        cv.put(COLUMN_TO_GOAL, toGoal);
        cv.put(COLUMN_ADDER, adder);
        cv.put(COLUMN_DONE, done);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Task Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void appendMethod(String row_id, String task,int goal, int to_goal, int adder, boolean done){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK_NAME,task);
        cv.put(COLUMN_GOAL,goal);
        cv.put(COLUMN_TO_GOAL,to_goal);
        cv.put(COLUMN_ADDER,adder);
        cv.put(COLUMN_DONE,done);
        db.update(TABLE_NAME,cv,"id=?",new String[]{row_id});
    }
    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }


}
