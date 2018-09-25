package com.koml2.fitassist.editdeleteexercise;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;
import com.koml2.fitassist.data.exercise.Exercise;

public interface EditDeleteExerciseContract {
    interface View extends BaseView<Presenter> {
        void setExerciseDataValues(Exercise exercise);

        void goToExerciseList();
    }

    interface Presenter extends BasePresenter {
        void getExercise(int id);

        void handleUpdateClick(
                int workoutId,
                int exerciseId,
                String name,
                String setsStr,
                String repsStr,
                String restTimeStr,
                String notes
        );

        void handleDeleteClick(
                int workoutId,
                int exerciseId,
                String name,
                String setsStr,
                String repsStr,
                String restTimeStr,
                String notes
        );
    }
}
