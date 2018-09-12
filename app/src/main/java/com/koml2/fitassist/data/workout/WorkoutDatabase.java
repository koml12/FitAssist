package com.koml2.fitassist.data.workout;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Workout.class}, version = 1)
public abstract class WorkoutDatabase extends RoomDatabase {
    private static WorkoutDatabase mWorkoutDatabase = null;

    public abstract WorkoutDao getWorkoutDao();

    public static WorkoutDatabase getWorkoutDatabase(Context context) {
        if (mWorkoutDatabase == null) {
            mWorkoutDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    WorkoutDatabase.class,
                    "workout-database").build();
        }
        return mWorkoutDatabase;
    }

    public static void destroyWorkoutDatabase() {
        mWorkoutDatabase = null;
    }
}
