package com.example.mrpeny.dailyhabittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mrpeny.dailyhabittracker.data.HabitContract.HabitEntry;
import com.example.mrpeny.dailyhabittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {
    private TextView habitsTextView;

    private HabitDbHelper mHabitDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inflate habitsTextView that will show database table data
        habitsTextView = (TextView) findViewById(R.id.habitsTextView);

        // Make an instance of HabitDbHelper class so that we can manage habit_tracker database
        mHabitDbHelper = new HabitDbHelper(this);

        addDummyHabitsToDatabase();
        displayDatabaseEntries();
    }

    /* Adds test dummy data to the habits database */
    private void addDummyHabitsToDatabase() {
        mHabitDbHelper.insertHabit("Shopping", HabitEntry.CATEGORY_HOUSE_KEEPING, 75);
        mHabitDbHelper.insertHabit("Let the dog for a walk", HabitEntry.CATEGORY_FAMILY, 60);
        mHabitDbHelper.insertHabit("Sailing on local lake", HabitEntry.CATEGORY_FREE_TIME, 240);
    }

    /* Displays all entries from habits database table */
    private void displayDatabaseEntries() {
        // Get entries from habits table
        Cursor cursor = mHabitDbHelper.queryHabits();

        try {
            // Set header for displaying the habits
            habitsTextView.setText(HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_CATEGORY + " - " +
                    HabitEntry.COLUMN_MINUTES_SPENT);

            // Getting column indices so that we can refer to retrieve them
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int categoryColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_CATEGORY);
            int minutesColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_MINUTES_SPENT);

            // Iterate through entries that cursor contains
            while (cursor.moveToNext()) {
                String name = cursor.getString(nameColumnIndex);
                int category = cursor.getInt(categoryColumnIndex);
                int minutes = cursor.getInt(minutesColumnIndex);

                habitsTextView.append("\n" + name + " - " + category + " - " + minutes);
            }

        } finally {
            cursor.close();
        }
    }
}
