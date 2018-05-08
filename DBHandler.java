package com.will.simplestrong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RoutinesDB.db";
    private static final int DATABASE_VERSION = 1;
    public Context context;

    // Routines Table & Columns
    public static final String TABLE_ROUTINES = "Routines";
    public static final String ROUTINE_ID = "_routine_id";
    public static final String ROUTINE_NAME = "routine_name";
    public static final String ROUTINE_DESCRIPTION = "routine_description";

    // RoutineExercises Table & Remaining Columns
    public static final String TABLE_ROUTINE_EXERCISES = "RoutineExercises";
    public static final String ROUTINE_DAY = "_routine_day";
    public static final String ROUTINE_EXERCISE = "_routine_exercise";
    public static final String ROUTINE_SETS = "routine_sets";
    public static final String ROUTINE_REPS = "routine_reps";


    // Create Table Statements
    final String CREATE_TABLE_ROUTINES = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ROUTINES + "(" +
            ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ROUTINE_NAME + " TEXT " +
            ")";

    final String CREATE_TABLE_ROUTINE_EXERCISES = "CREATE TABLE IF NOT EXISTS " +
            TABLE_ROUTINE_EXERCISES + "(" +
            ROUTINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ROUTINE_DAY + " TEXT, " +
            ROUTINE_EXERCISE + " TEXT, " +
            ROUTINE_SETS + " INTEGER, " +
            ROUTINE_REPS + " INTEGER " +
            ")";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ROUTINES);
        db.execSQL(CREATE_TABLE_ROUTINE_EXERCISES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINE_EXERCISES);
        onCreate(db);
    }

    // New Routine
    public void createRoutine(Routine routine) {
        ContentValues values = new ContentValues();
        values.put(ROUTINE_NAME, routine.getName());
        values.put(ROUTINE_DESCRIPTION, routine.getDescription());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ROUTINES, null, values);
        db.close();
    }

    // New Routine's Days & Exercises
    public void createRoutineExercise(RoutineExercise routineExercise) {
        ContentValues values = new ContentValues();
        values.put(ROUTINE_ID, routineExercise.get_id());
        values.put(ROUTINE_DAY, routineExercise.get_day());
        values.put(ROUTINE_EXERCISE, routineExercise.get_exercise());
        values.put(ROUTINE_SETS, routineExercise.getSets());
        values.put(ROUTINE_REPS, routineExercise.getReps());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ROUTINE_EXERCISES, null, values);
        db.close();
    }

    // Delete Routine
    public void deleteRecipe(int routineId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ROUTINES, ROUTINE_ID + " = ?", new String[]{String.valueOf(routineId)});
        db.delete(TABLE_ROUTINE_EXERCISES, ROUTINE_ID + " = ?", new String[]{String.valueOf(routineId)});
    }

    // Get all Routines
    public List<Routine> getRoutines() {
        String query = "SELECT * FROM " + TABLE_ROUTINES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<Routine> routineList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Routine routine = new Routine();

            int fetchedRoutineId =
                    cursor.getInt(cursor.getColumnIndex(ROUTINE_ID));

            String fetchedRoutineName =
                    cursor.getString(cursor.getColumnIndex(ROUTINE_NAME));

            String fetchedRoutineDescription =
                    cursor.getString(cursor.getColumnIndex(ROUTINE_DESCRIPTION));

            routine.set_id(fetchedRoutineId);
            routine.setName(fetchedRoutineName);
            routine.setDescription(fetchedRoutineDescription);

            routineList.add(routine);

        }
        Log.i("getRoutines", "Got Routines: " + routineList);
        cursor.close();
        db.close();

        return routineList;
    }

    // Get Number of Days for Routine
    public int getDays(int routineId) {
        String query = "SELECT " + ROUTINE_DAY +
                " FROM " + TABLE_ROUTINE_EXERCISES +
                " WHERE " + ROUTINE_ID + "=" + routineId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToNext();
        return cursor.getCount();
    }


}



