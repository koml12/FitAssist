package com.koml2.fitassist.addexercise;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.data.exercise.Exercise;

public class AddExercisePresenter implements AddExerciseContract.Presenter {

    private FitAssistRepository mFitAssistRepository;
    private AddExerciseContract.View mAddExerciseView;

    public AddExercisePresenter(@NonNull FitAssistRepository repository, @NonNull AddExerciseContract.View view) {
        mFitAssistRepository = repository;
        mAddExerciseView = view;
        mAddExerciseView.setPresenter(this);
    }

    @Override
    public void onAddButtonClick(String name, String repsStr, String setsStr, String restTimeStr, String notes) {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setReps(Integer.parseInt(repsStr));
        exercise.setSets(Integer.parseInt(setsStr));
        exercise.setRestTime(Integer.parseInt(restTimeStr));
        exercise.setNotes(notes);

        AddExerciseTask addExerciseTask = new AddExerciseTask(mAddExerciseView, exercise);
        addExerciseTask.execute(mFitAssistRepository);

    }

    @Override
    public void start() {
        mAddExerciseView.showAddExerciseForm();
    }

    private static class AddExerciseTask extends AsyncTask<FitAssistRepository, Void, Void> {

        private AddExerciseContract.View mView;
        private Exercise mExercise;

        AddExerciseTask(AddExerciseContract.View view, Exercise exercise) {
            mView = view;
            mExercise = exercise;
        }

        @Override
        protected Void doInBackground(FitAssistRepository... exerciseRepositories) {
            exerciseRepositories[0].insertExercise(mExercise);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mView.goBackToViewWorkout();
        }
    }
}
