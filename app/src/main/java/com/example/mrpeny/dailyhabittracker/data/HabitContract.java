package com.example.mrpeny.dailyhabittracker.data;

import android.provider.BaseColumns;

/**
 * API contract for the HabitTracker app.
 */

public class HabitContract {

    // Empty constructor for preventing accidental instantiation of this class
    private HabitContract() {
    }

    /**
     * Public inner class representing habits table in the database.
     * It included constant variables for column names.
     * Each entry in the table represents a daily habit.
     */
    public static final class HabitEntry implements BaseColumns {
        /**
         * Name of the habit entry.
         * <p>
         * Type: TEXT
         */
        public static final String COLUMN_HABIT_NAME = "name";
        /**
         * Category of the habit.
         * <p>
         * The only possible values are {@link #CATEGORY_UNKNOWN}, {@link #CATEGORY_FAMILY},
         * {@link #CATEGORY_WORK}, {@link #CATEGORY_HOUSE_KEEPING}, {@link #CATEGORY_FREE_TIME}
         * <p>
         * Type: INTEGER
         */
        public static final String COLUMN_HABIT_CATEGORY = "category";
        /**
         * Number of minutes spent with that habit.
         * <p>
         * Type: INTEGER
         * <p>
         * Min value: 1, Max value: 1440
         */
        public static final String COLUMN_MINUTES_SPENT = "minutes";
        /**
         * Possible categories of a habit entry.
         */
        public static final int CATEGORY_UNKNOWN = 0;
        public static final int CATEGORY_FAMILY = 1;
        public static final int CATEGORY_WORK = 2;
        public static final int CATEGORY_HOUSE_KEEPING = 3;
        public static final int CATEGORY_FREE_TIME = 4;
        /**
         * Name of database table containing habit entries
         */
        static final String TABLE_NAME = "habits";
        /**
         * Unique ID of each habit entry.
         * <p>
         * Type: INTEGER
         */
        static final String _ID = BaseColumns._ID;
    }
}
