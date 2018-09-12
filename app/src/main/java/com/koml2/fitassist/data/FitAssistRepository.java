package com.koml2.fitassist.data;

import android.content.Context;
import com.koml2.fitassist.data.exercise.Exercise;
import com.koml2.fitassist.data.exercise.ExerciseDao;
import com.koml2.fitassist.data.workout.Workout;
import com.koml2.fitassist.data.workout.WorkoutDao;

import java.util.List;

public class FitAssistRepository {
    private static ExerciseDao mExerciseDao = null;

    private static WorkoutDao mWorkoutDao = null;

    private static FitAssistRepository instance = null;

    // Prevent manual instantiation.
    private FitAssistRepository() {  }

    public static FitAssistRepository getInstance(Context context) {
        if (instance == null) {
            instance = new FitAssistRepository();
            mExerciseDao = FitAssistDatabase.getFitAssistDatabase(context).getExerciseDao();
            mWorkoutDao = FitAssistDatabase.getFitAssistDatabase(context).getWorkoutDao();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
        mExerciseDao = null;
        FitAssistDatabase.destroyFitAssistDatabase();
    }

    // Exercise methods.
    public void insertExercise(Exercise exercise) {
        mExerciseDao.insertExercise(exercise);
    }

    public void updateExercise(Exercise exercise) {
        mExerciseDao.updateExercise(exercise);
    }

    public void deleteExercise(Exercise exercise) {
        mExerciseDao.deleteExercise(exercise);
    }

    public List<Exercise> getAllExercises() {
        return mExerciseDao.getAllExercises();
    }

    public Exercise getExerciseById(int id) {
        return mExerciseDao.getExerciseById(id);
    }


    // Workout methods.
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
