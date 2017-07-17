package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by Karnoha on 17.07.2017.
 */

public final class HabitContract {

    private HabitContract(){}

    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "running";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DISTANCE = "distance";
        public static final String COLUMN_AVG_HB = "heartbeat";
        public static final String COLUMN_LOCATION = "location";

    }
}
