package com.koml2.fitassist.editdeleteexercise;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.exercise.Exercise;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.viewworkout.ViewWorkoutFragment;
import com.koml2.fitassist.viewworkout.ViewWorkoutPresenter;

public class EditDeleteExerciseFragment extends Fragment implements EditDeleteExerciseContract.View {

    /**
     * Presenter for the Fragment.
     */
    private EditDeleteExerciseContract.Presenter mPresenter;

    /**
     * The ID of the exercise that we want to update or delete.
     */
    private int mExerciseId;

    private int mWorkoutId;

    private EditText mExerciseNameEditText;
    private EditText mSetsEditText;
    private EditText mRepsEditText;
    private EditText mRestTimeEditText;
    private EditText mNotesEditText;
    private Button mUpdateExerciseButton;
    private Button mDeleteExerciseButton;


    public EditDeleteExerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param exerciseId  Primary key ID of the selected exercise.
     * @return A new instance of fragment EditDeleteExerciseFragment.
     */
    public static EditDeleteExerciseFragment newInstance(int workoutId, int exerciseId) {
        EditDeleteExerciseFragment fragment = new EditDeleteExerciseFragment();
        Bundle args = new Bundle();
        args.putInt("workoutId", workoutId);
        args.putInt("exerciseId", exerciseId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            // TODO: Handle error conditions if either of these is 0.
            mExerciseId = bundle.getInt("exerciseId", 0);
            mWorkoutId = bundle.getInt("workoutId", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_delete_exercise, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExerciseNameEditText = view.findViewById(R.id.et_edit_exercise_name);
        mSetsEditText = view.findViewById(R.id.et_edit_exercise_sets);
        mRepsEditText = view.findViewById(R.id.et_edit_exercise_reps);
        mRestTimeEditText = view.findViewById(R.id.et_edit_exercise_rest_time);
        mNotesEditText = view.findViewById(R.id.et_edit_exercise_notes);
        mUpdateExerciseButton = view.findViewById(R.id.btn_edit_exercise_update_button);
        mDeleteExerciseButton = view.findViewById(R.id.btn_edit_exercise_delete_button);

        mPresenter.getExercise(mExerciseId);

        mUpdateExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mExerciseNameEditText.getText().toString();
                String sets = mSetsEditText.getText().toString();
                String reps = mRepsEditText.getText().toString();
                String restTime = mRestTimeEditText.getText().toString();
                String notes = mNotesEditText.getText().toString();

                mPresenter.handleUpdateClick(mWorkoutId, mExerciseId, name, reps, sets, restTime, notes);
            }
        });

        mDeleteExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mExerciseNameEditText.getText().toString();
                String sets = mSetsEditText.getText().toString();
                String reps = mRepsEditText.getText().toString();
                String restTime = mRestTimeEditText.getText().toString();
                String notes = mNotesEditText.getText().toString();

                mPresenter.handleDeleteClick(mWorkoutId, mExerciseId, name, sets, reps, restTime, notes);
            }
        });

    }

    @Override
    public void setExerciseDataValues(Exercise exercise) {
        mExerciseNameEditText.setText(exercise.getName());
        mSetsEditText.setText(String.valueOf(exercise.getSets()));
        mRepsEditText.setText(String.valueOf(exercise.getReps()));
        mRestTimeEditText.setText(String.valueOf(exercise.getRestTime()));
        mNotesEditText.setText(exercise.getNotes());
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void goToExerciseList() {
        ViewWorkoutFragment viewWorkoutFragment = ViewWorkoutFragment.newInstance(0);
        ViewWorkoutPresenter presenter = new ViewWorkoutPresenter(
                FitAssistRepository.getInstance(getActivity().getApplicationContext()),
                viewWorkoutFragment
        );

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.remove(this);
        transaction.add(R.id.fragment_container, viewWorkoutFragment);

        manager.popBackStackImmediate();
        transaction.commit();
    }

    @Override
    public void setPresenter(EditDeleteExerciseContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
