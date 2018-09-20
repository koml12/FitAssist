package com.koml2.fitassist.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import com.koml2.fitassist.data.exercise.Exercise;
import com.koml2.fitassist.data.exercise.ExerciseDao;
import com.koml2.fitassist.data.workout.Workout;
import com.koml2.fitassist.data.workout.WorkoutDao;

@Database(entities = {Exercise.class, Workout.class}, version = 1)
public abstract class FitAssistDatabase extends RoomDatabase {

    private static FitAssistDatabase INSTANCE;

    public abstract ExerciseDao getExerciseDao();

    public abstract WorkoutDao getWorkoutDao();

    public static FitAssistDatabase getFitAssistDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    FitAssistDatabase.class,
                    "exercise-database"
            ).build();
        }
        return INSTANCE;
    }

    public static void destroyFitAssistDatabase() {
        INSTANCE = null;
    }
}
