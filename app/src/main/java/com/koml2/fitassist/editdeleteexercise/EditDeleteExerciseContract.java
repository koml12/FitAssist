package com.koml2.fitassist.editdeleteexercise;

import com.koml2.fitassist.BasePresenter;
import com.koml2.fitassist.BaseView;
import com.koml2.fitassist.data.exercise.Exercise;

public interface EditDeleteExerciseContract {
    interface View extends BaseView<Presenter> {
        void setExerciseDataValues(Exercise exercise);

        void onUpdateClick(String name, String setsStr, String repsStr, String restTimeStr, String notes);

        void onDeleteClick(String name, String setsStr, String repsStr, String restTimeStr, String notes);

        void goToExerciseList();
    }

    interface Presenter extends BasePresenter {
        void getExercise(int id);

        void handleUpdateClick(int id, String name, String setsStr, String repsStr, String restTimeStr, String notes);

        void handleDeleteClick(int id, String name, String setsStr, String repsStr, String restTimeStr, String notes);
    }
}
