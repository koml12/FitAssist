package com.koml2.fitassist.viewworkout;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.koml2.fitassist.data.Exercise;
import com.koml2.fitassist.data.ExerciseRepository;

import java.util.List;

public class ViewWorkoutPresenter implements ViewWorkoutContract.Presenter {

    private ExerciseRepository mExerciseRepository;
    private ViewWorkoutContract.View mWorkoutView;


    public ViewWorkoutPresenter(@NonNull ExerciseRepository repository, @NonNull ViewWorkoutContract.View view) {
        mExerciseRepository = repository;
        mWorkoutView = view;

        mWorkoutView.setPresenter(this);
    }

    @Override
    public void loadExercises() {
        GetExercisesTask getExercisesTask = new GetExercisesTask(mWorkoutView);
        getExercisesTask.execute(mExerciseRepository);
    }

    @Override
    public void handleAddButtonClick(String name) {
        mWorkoutView.startAddExercise();

        /*
        Adds inside the current fragment, which we don't want.

        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setReps(5);
        exercise.setSets(5);
        exercise.setRestTime(90);
        exercise.setNotes("");

        AddExerciseTask addExerciseTask = new AddExerciseTask(exercise, mWorkoutView);
        addExerciseTask.execute(mExerciseRepository);
        */
    }

    @Override
    public void start() {
        loadExercises();
    }


    private static class GetExercisesTask extends AsyncTask<ExerciseRepository, Void, List<Exercise>> {
        private ViewWorkoutContract.View mView;

        GetExercisesTask(ViewWorkoutContract.View view) {
            mView = view;
        }

        @Override
        protected List<Exercise> doInBackground(ExerciseRepository... exerciseRepositories) {
            return exerciseRepositories[0].getAllExercises();
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            super.onPostExecute(exercises);
            mView.showExercises(exercises);
        }
    }

    private static class AddExerciseTask extends AsyncTask<ExerciseRepository, Void, List<Exercise>> {
        private ViewWorkoutContract.View mView;
        private Exercise mExercise;
        private ExerciseRepository mExerciseRepository;

        AddExerciseTask(Exercise exercise, ViewWorkoutContract.View view) {
            mExercise = exercise;
            mView = view;
        }

        @Override
        protected List<Exercise> doInBackground(ExerciseRepository... exerciseRepositories) {
            mExerciseRepository = exerciseRepositories[0];
            exerciseRepositories[0].insertExercise(mExercise);
            return exerciseRepositories[0].getAllExercises();
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            super.onPostExecute(exercises);
            GetExercisesTask getExercisesTask = new GetExercisesTask(mView);
            getExercisesTask.execute(mExerciseRepository);
        }
    }

}
