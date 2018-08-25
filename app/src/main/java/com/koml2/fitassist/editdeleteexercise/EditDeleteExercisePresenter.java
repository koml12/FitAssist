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

    }

    @Override
    public void handleDeleteClick(int id, String name, String setsStr, String repsStr, String restTimeStr, String notes) {

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
}
