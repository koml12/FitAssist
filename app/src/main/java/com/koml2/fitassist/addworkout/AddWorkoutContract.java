package com.koml2.fitassist.addworkout;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;

public interface AddWorkoutContract {
    interface View extends BaseView<AddWorkoutPresenter> {
        void showAddWorkoutForm();

        void toViewWorkoutList();
    }

    interface Presenter extends BasePresenter {
        void handleAddButtonClick(String workoutName);
    }
}
