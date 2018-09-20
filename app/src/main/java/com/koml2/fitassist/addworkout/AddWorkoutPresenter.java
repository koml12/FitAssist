package com.koml2.fitassist.addworkout;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.data.workout.Workout;

public class AddWorkoutPresenter implements AddWorkoutContract.Presenter {

    private FitAssistRepository mDataRepository;
    private AddWorkoutContract.View mView;

    public AddWorkoutPresenter(@NonNull FitAssistRepository repository, @NonNull AddWorkoutContract.View view) {
        mDataRepository = repository;
        mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void handleAddButtonClick(String workoutName) {
        Workout workout = new Workout(workoutName);
        AddWorkoutTask addWorkoutTask = new AddWorkoutTask(mView, workout);
        addWorkoutTask.execute(mDataRepository);
    }

    @Override
    public void start() {

    }

    private static class AddWorkoutTask extends AsyncTask<FitAssistRepository, Void, Void> {

        private Workout mWorkout;
        private AddWorkoutContract.View mView;

        AddWorkoutTask(AddWorkoutContract.View view, Workout workout) {
            mWorkout = workout;
            mView = view;
        }

        @Override
        protected Void doInBackground(FitAssistRepository... fitAssistRepositories) {
            fitAssistRepositories[0].insertWorkout(mWorkout);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mView.toViewWorkoutList();
        }
    }
}
