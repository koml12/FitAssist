package com.koml2.fitassist.addexercise;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.ExerciseRepository;
import com.koml2.fitassist.viewworkout.ViewWorkoutFragment;
import com.koml2.fitassist.viewworkout.ViewWorkoutPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddExerciseFragment} interface
 * to handle interaction events.
 * Use the {@link AddExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExerciseFragment extends Fragment implements AddExerciseContract.View {

    private AddExerciseContract.Presenter mPresenter;

    private EditText mExerciseNameEditText;
    private EditText mSetsEditText;
    private EditText mRepsEditText;
    private EditText mRestTimeEditText;
    private EditText mNotesEditText;
    private Button mAddExerciseButton;

    public AddExerciseFragment() {
        // Required empty public constructor
    }

    public static AddExerciseFragment newInstance() {
        return new AddExerciseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_exercise, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mExerciseNameEditText = view.findViewById(R.id.et_add_exercise_name);
        mSetsEditText = view.findViewById(R.id.et_add_exercise_sets);
        mRepsEditText = view.findViewById(R.id.et_add_exercise_reps);
        mRestTimeEditText = view.findViewById(R.id.et_add_exercise_rest_time);
        mNotesEditText = view.findViewById(R.id.et_add_exercise_notes);
        mAddExerciseButton = view.findViewById(R.id.btn_add_exercise_add_button);

        mAddExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String exerciseName = mExerciseNameEditText.getText().toString();
                String sets = mSetsEditText.getText().toString();
                String reps = mRepsEditText.getText().toString();
                String restTime = mRestTimeEditText.getText().toString();
                String notes = mNotesEditText.getText().toString();
                mPresenter.onAddButtonClick(exerciseName, reps, sets, restTime, notes);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showAddExerciseForm() {

    }

    @SuppressWarnings("Duplicates")
    @Override
    public void goBackToViewWorkout() {
        ViewWorkoutFragment viewWorkoutFragment = ViewWorkoutFragment.newInstance();
        ViewWorkoutPresenter viewWorkoutPresenter = new ViewWorkoutPresenter(
                ExerciseRepository.getInstance(getActivity().getApplicationContext()),
                viewWorkoutFragment
        );

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.detach(this);
        transaction.replace(R.id.fragment_view_workout_container, viewWorkoutFragment).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (AddExerciseContract.Presenter) presenter;
    }
}
