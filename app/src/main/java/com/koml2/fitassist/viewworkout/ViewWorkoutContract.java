package com.koml2.fitassist.viewworkout;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;
import com.koml2.fitassist.data.Exercise;

import java.util.List;

public interface ViewWorkoutContract {
    interface View extends BaseView {
        void refreshExercises();

        void showExercises(List<Exercise> exercises);

        void onAddButtonClick(android.view.View view);

        void startAddExercise();
    }

    interface Presenter extends BasePresenter {
        void loadExercises();

        void handleAddButtonClick(String name);
    }
}
