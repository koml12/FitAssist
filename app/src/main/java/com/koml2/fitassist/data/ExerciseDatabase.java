package com.koml2.fitassist.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Exercise.class}, version = 2)
public abstract class ExerciseDatabase extends RoomDatabase {

    private static ExerciseDatabase INSTANCE;

    public abstract ExerciseDao getExerciseDao();

    public static ExerciseDatabase getExerciseDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ExerciseDatabase.class,
                    "exercise-database"
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyExerciseDatabase() {
        INSTANCE = null;
    }
}
