package com.koml2.fitassist.viewworkout;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.koml2.fitassist.data.exercise.Exercise;
import com.koml2.fitassist.data.FitAssistRepository;

import java.util.List;

public class ViewWorkoutPresenter implements ViewWorkoutContract.Presenter {

    private FitAssistRepository mFitAssistRepository;
    private ViewWorkoutContract.View mWorkoutView;


    public ViewWorkoutPresenter(@NonNull FitAssistRepository repository, @NonNull ViewWorkoutContract.View view) {
        mFitAssistRepository = repository;
        mWorkoutView = view;

        mWorkoutView.setPresenter(this);
    }

    @Override
    public void loadExercises(int workoutId) {
        GetExercisesTask getExercisesTask = new GetExercisesTask(mWorkoutView, workoutId);
        getExercisesTask.execute(mFitAssistRepository);
    }

    @Override
    public void handleAddButtonClick(String name) {
        mWorkoutView.startAddExercise();
    }

    @Override
    public void start() {

    }


    private static class GetExercisesTask extends AsyncTask<FitAssistRepository, Void, List<Exercise>> {
        private ViewWorkoutContract.View mView;
        private int mWorkoutId;

        GetExercisesTask(ViewWorkoutContract.View view, int workoutId) {
            mView = view;
            mWorkoutId = workoutId;
        }

        @Override
        protected List<Exercise> doInBackground(FitAssistRepository... exerciseRepositories) {
            return exerciseRepositories[0].getExercisesForWorkout(mWorkoutId);
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            super.onPostExecute(exercises);
            mView.showExercises(exercises);
        }
    }

    private static class AddExerciseTask extends AsyncTask<FitAssistRepository, Void, List<Exercise>> {
        private ViewWorkoutContract.View mView;
        private Exercise mExercise;
        private FitAssistRepository mFitAssistRepository;
        private int mWorkoutId;

        AddExerciseTask(Exercise exercise, ViewWorkoutContract.View view, int workoutId) {
            mExercise = exercise;
            mView = view;
            mWorkoutId = workoutId;
        }

        @Override
        protected List<Exercise> doInBackground(FitAssistRepository... exerciseRepositories) {
            mFitAssistRepository = exerciseRepositories[0];
            exerciseRepositories[0].insertExercise(mExercise);
            return exerciseRepositories[0].getAllExercises();
        }

        @Override
        protected void onPostExecute(List<Exercise> exercises) {
            super.onPostExecute(exercises);
            GetExercisesTask getExercisesTask = new GetExercisesTask(mView, mWorkoutId);
            getExercisesTask.execute(mFitAssistRepository);
        }
    }

}
