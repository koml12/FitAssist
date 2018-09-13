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
    public void loadExercises() {
        GetExercisesTask getExercisesTask = new GetExercisesTask(mWorkoutView);
        getExercisesTask.execute(mFitAssistRepository);
    }

    @Override
    public void handleAddButtonClick(String name) {
        mWorkoutView.startAddExercise();
    }

    @Override
    public void start() {
        loadExercises();
    }


    private static class GetExercisesTask extends AsyncTask<FitAssistRepository, Void, List<Exercise>> {
        private ViewWorkoutContract.View mView;

        GetExercisesTask(ViewWorkoutContract.View view) {
            mView = view;
        }

        @Override
        protected List<Exercise> doInBackground(FitAssistRepository... exerciseRepositories) {
            return exerciseRepositories[0].getAllExercises();
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

        AddExerciseTask(Exercise exercise, ViewWorkoutContract.View view) {
            mExercise = exercise;
            mView = view;
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
            GetExercisesTask getExercisesTask = new GetExercisesTask(mView);
            getExercisesTask.execute(mFitAssistRepository);
        }
    }

}
