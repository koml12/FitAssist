package com.koml2.fitassist.addexercise;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;

public interface AddExerciseContract {
    interface View extends BaseView {
        void showAddExerciseForm();

        void goBackToViewWorkout();
    }

    interface Presenter extends BasePresenter {
        void onAddButtonClick(String name,
                              String repsStr,
                              String setsStr,
                              String restTimeStr,
                              String notes,
                              int workoutId);
    }
}
