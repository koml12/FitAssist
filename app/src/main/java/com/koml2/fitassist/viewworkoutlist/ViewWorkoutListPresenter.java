package com.koml2.fitassist.viewworkoutlist;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.data.workout.Workout;
import com.koml2.fitassist.editdeleteexercise.EditDeleteExercisePresenter;
import com.koml2.fitassist.viewworkout.ViewWorkoutContract;
import com.koml2.fitassist.viewworkout.ViewWorkoutPresenter;

import java.util.List;

public class ViewWorkoutListPresenter implements ViewWorkoutListContract.Presenter {
    private ViewWorkoutListContract.View mView;
    private FitAssistRepository mFitAssistRepository;

    public ViewWorkoutListPresenter(@NonNull FitAssistRepository repository, @NonNull ViewWorkoutListContract.View view) {
        mView = view;
        mFitAssistRepository = repository;
        mView.setPresenter(this);
    }

    @Override
    public void loadWorkouts() {
        GetWorkoutsTask getWorkoutsTask = new GetWorkoutsTask(mView);
        getWorkoutsTask.execute(mFitAssistRepository);
    }

    @Override
    public void start() {

    }

    private static class GetWorkoutsTask extends AsyncTask<FitAssistRepository, Void, List<Workout>> {
        private ViewWorkoutListContract.View mView;

        GetWorkoutsTask(ViewWorkoutListContract.View view) {
            mView = view;
        }

        @Override
        protected List<Workout> doInBackground(FitAssistRepository... fitAssistRepositories) {
            return fitAssistRepositories[0].getAllWorkouts();
        }

        @Override
        protected void onPostExecute(List<Workout> workouts) {
            super.onPostExecute(workouts);
            mView.showWorkouts(workouts);
        }
    }
}
