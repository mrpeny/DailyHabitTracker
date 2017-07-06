package com.example.mrpeny.dailyhabittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mrpeny.dailyhabittracker.data.HabitContract.HabitEntry;

/**
 * Helper class managing Habit Tracker app's database
 */

public class HabitDbHelper extends SQLiteOpenHelper {
    /**
     * Name of the file storing the database
     */
    private static final String DATABASE_NAME = "habit_tracker.db";
    /**
     * Current version of the database
     */
    private static final int DATABASE_VERSION = 1;
    // Log tag used for debugging purposes
    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /**
     * Constructs a new {@link HabitDbHelper} with the constant database name and version
     *
     * @param context context of this app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL String that creates the habits table in the database
        final String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL, "
                + HabitEntry.COLUMN_HABIT_CATEGORY + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_MINUTES_SPENT + " INTEGER NOT NULL DEFAULT 1);";

        db.execSQL(SQL_CREATE_HABITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SQL statement that deletes habits database table
        final String DELETE_DATABASE_SQL = "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

        // Execute DELETE SQL statement defined above
        db.execSQL(DELETE_DATABASE_SQL);
        // Upgrade the new database
        onCreate(db);
    }

    /**
     * Inserts a habit into habits database table using the given arguments
     *
     * @param name     the name of the habit
     * @param category the category of the habit
     * @param minutes  the number of minutes spent with the habit
     */
    public void insertHabit(String name, int category, int minutes) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(HabitEntry.COLUMN_HABIT_NAME, name);
        contentValues.put(HabitEntry.COLUMN_HABIT_CATEGORY, category);
        contentValues.put(HabitEntry.COLUMN_MINUTES_SPENT, minutes);

        database.insert(HabitEntry.TABLE_NAME, null, contentValues);
    }

    /**
     * Queries the habits from the corresponding database table
     *
     * @return the Cursor object containing the result of the query
     */
    public Cursor queryHabits() {
        Cursor cursor;

        SQLiteDatabase database = getReadableDatabase();

        // Defining the columns we need to retrieve
        String[] projection = {
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_CATEGORY,
                HabitEntry.COLUMN_MINUTES_SPENT};

        // Query database for habits table with the given column names without any specific
        // selection, order or grouping
        cursor = database.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }
}
