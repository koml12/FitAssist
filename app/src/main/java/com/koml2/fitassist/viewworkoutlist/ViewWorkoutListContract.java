package com.koml2.fitassist.viewworkoutlist;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;
import com.koml2.fitassist.data.workout.Workout;

import java.util.List;

public interface ViewWorkoutListContract {
    interface View extends BaseView<ViewWorkoutListPresenter> {
        void showWorkouts(List<Workout> workouts);

        void sendToAddWorkout();

        void toViewWorkout(int workoutId);
    }

    interface Presenter extends BasePresenter {
        void loadWorkouts();
    }
}
