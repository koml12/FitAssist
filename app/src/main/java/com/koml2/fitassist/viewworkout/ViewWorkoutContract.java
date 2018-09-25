package com.koml2.fitassist.viewworkout;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;
import com.koml2.fitassist.data.exercise.Exercise;

import java.util.List;

public interface ViewWorkoutContract {
    interface View extends BaseView<ViewWorkoutPresenter> {
        void refreshExercises();

        void showExercises(List<Exercise> exercises);

        void onAddButtonClick(android.view.View view);

        void startAddExercise();

        void startEditDeleteExercise(int workoutId, int exerciseId);
    }

    interface Presenter extends BasePresenter {
        void loadExercises(int workoutId);

        void handleAddButtonClick(String name);
    }
}
