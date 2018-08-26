package com.koml2.fitassist.editdeleteexercise;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.koml2.fitassist.data.Exercise;
import com.koml2.fitassist.data.ExerciseRepository;

public class EditDeleteExercisePresenter implements EditDeleteExerciseContract.Presenter {

    /**
     * The database connection. Acts as the "Model".
     */
    private ExerciseRepository mExerciseRepository;

    /**
     * The "View" the presenter is attached to and calls update methods on.
     */
    private EditDeleteExerciseContract.View mEditDeleteExerciseView;


    public EditDeleteExercisePresenter(@NonNull ExerciseRepository repository, @NonNull EditDeleteExerciseContract.View view) {
        mExerciseRepository = repository;
        mEditDeleteExerciseView = view;

        mEditDeleteExerciseView.setPresenter(this);
    }

    @Override
    public void getExercise(int id) {
        if (id == 0) {
            return;
        }
        GetExerciseTask getExerciseTask = new GetExerciseTask(mEditDeleteExerciseView, id);
        getExerciseTask.execute(mExerciseRepository);
    }

    @Override
    public void handleUpdateClick(int id, String name, String setsStr, String repsStr, String restTimeStr, String notes) {
        int sets = Integer.parseInt(setsStr);
        int reps = Integer.parseInt(repsStr);
        int restTime = Integer.parseInt(restTimeStr);

        Exercise exercise = new Exercise(id, name, sets, reps, restTime, notes);
        UpdateExerciseTask updateExerciseTask = new UpdateExerciseTask(mEditDeleteExerciseView, exercise);
        updateExerciseTask.execute(mExerciseRepository);
    }

    @Override
    public void handleDeleteClick(int id, String name, String setsStr, String repsStr, String restTimeStr, String notes) {
        int sets = Integer.parseInt(setsStr);
        int reps = Integer.parseInt(repsStr);
        int restTime = Integer.parseInt(restTimeStr);

        Exercise exercise = new Exercise(id, name, sets, reps, restTime, notes);
        DeleteExerciseTask deleteExerciseTask = new DeleteExerciseTask(mEditDeleteExerciseView, exercise);
        deleteExerciseTask.execute(mExerciseRepository);
    }

    @Override
    public void start() {

    }

    /**
     * Handles getting an exercise by its ID. Updates the view to reflect the retrieved exercise.
     */
    public static class GetExerciseTask extends AsyncTask<ExerciseRepository, Void, Exercise> {
        private EditDeleteExerciseContract.View mView;
        private int mExerciseId;

        GetExerciseTask(EditDeleteExerciseContract.View view, int id) {
            mView = view;
            mExerciseId = id;
        }

        @Override
        protected Exercise doInBackground(ExerciseRepository... exerciseRepositories) {
            return exerciseRepositories[0].getExerciseById(mExerciseId);
        }

        @Override
        protected void onPostExecute(Exercise exercise) {
            super.onPostExecute(exercise);
            mView.setExerciseDataValues(exercise);
        }
    }

    /**
     * Task handler for updating an exercise's information.
     * Sends the user back to the exercise list after the operation was completed.
     */
    public static class UpdateExerciseTask extends AsyncTask<ExerciseRepository, Void, Void> {
        private EditDeleteExerciseContract.View mView;
        private Exercise mExercise;

        UpdateExerciseTask(EditDeleteExerciseContract.View view, Exercise exercise) {
            mView = view;
            mExercise = exercise;
        }

        @Override
        protected Void doInBackground(ExerciseRepository... exerciseRepositories) {
            exerciseRepositories[0].updateExercise(mExercise);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mView.goToExerciseList();
        }
    }

    /**
     * Task handler for deleting an exercise from the list.
     * Sends the user back to the exercise list when the task has been completed.
     */
    public static class DeleteExerciseTask extends AsyncTask<ExerciseRepository, Void, Void> {
        private EditDeleteExerciseContract.View mView;
        private Exercise mExercise;

        DeleteExerciseTask(EditDeleteExerciseContract.View view, Exercise exercise) {
            mView = view;
            mExercise = exercise;
        }

        @Override
        protected Void doInBackground(ExerciseRepository... exerciseRepositories) {
            exerciseRepositories[0].deleteExercise(mExercise);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mView.goToExerciseList();
        }
    }
}