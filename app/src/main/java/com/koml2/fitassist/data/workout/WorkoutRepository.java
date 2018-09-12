package com.koml2.fitassist.data.workout;

import android.content.Context;

import java.util.List;

public class WorkoutRepository {
    private static WorkoutDao mWorkoutDao = null;

    private static WorkoutRepository mWorkoutRepository = null;

    private WorkoutRepository() { }

    public static WorkoutRepository getWorkoutRepository(Context context) {
        if (mWorkoutRepository == null) {
            mWorkoutRepository = new WorkoutRepository();
            mWorkoutDao = WorkoutDatabase.getWorkoutDatabase(context).getWorkoutDao();
        }
        return mWorkoutRepository;
    }

    public static void destroyWorkoutRepository() {
        mWorkoutRepository = null;
        mWorkoutDao = null;
    }

    public void insertWorkout(Workout workout) {
        mWorkoutDao.insertWorkout(workout);
    }

    public void updateWorkout(Workout workout) {
        mWorkoutDao.updateWorkout(workout);
    }

    public void deleteWorkout(Workout workout) {
        mWorkoutDao.deleteWorkout(workout);
    }

    public List<Workout> getAllWorkouts() {
        return mWorkoutDao.getAllWorkouts();
    }

    public Workout getWorkoutById(int id) {
        return mWorkoutDao.getWorkoutById(id);
    }
}
