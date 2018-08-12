package com.koml2.fitassist.data;

import android.content.Context;

import java.util.List;

public class ExerciseRepository {
    private static ExerciseDao mExerciseDao = null;

    private static ExerciseRepository instance = null;

    // Prevent manual instantiation.
    private ExerciseRepository() {  }

    public static ExerciseRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ExerciseRepository();
            mExerciseDao = ExerciseDatabase.getExerciseDatabase(context).getExerciseDao();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
        mExerciseDao = null;
    }

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

}
