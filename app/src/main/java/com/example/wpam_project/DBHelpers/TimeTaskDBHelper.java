package com.example.wpam_project.DBHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TimeTaskDBHelper extends SQLiteOpenHelper {


    private Context context;
    private static final String DATABASE_NAME = "TimeTask.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "time_tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK_NAME = "task_name";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_DONE = "task_done";


    public TimeTaskDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TASK_NAME + " TEXT, " +
                        COLUMN_TIME + " INTEGER, " +
                        COLUMN_DONE + " BOOLEAN);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTask(String task, int time, boolean done){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TASK_NAME, task);
        cv.put(COLUMN_TIME, time);
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

    public void taskChecked(String row_id, String task, int time, boolean done){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TASK_NAME,task);
        cv.put(COLUMN_TIME,time);
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
