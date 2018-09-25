package com.koml2.fitassist.addexercise;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.viewworkout.ViewWorkoutFragment;
import com.koml2.fitassist.viewworkout.ViewWorkoutPresenter;

public class AddExerciseFragment extends Fragment implements AddExerciseContract.View {

    private AddExerciseContract.Presenter mPresenter;

    private int mWorkoutId;

    private EditText mExerciseNameEditText;
    private EditText mSetsEditText;
    private EditText mRepsEditText;
    private EditText mRestTimeEditText;
    private EditText mNotesEditText;
    private Button mAddExerciseButton;

    public AddExerciseFragment() {
        // Required empty public constructor
    }

    public static AddExerciseFragment newInstance(int workoutId) {
        AddExerciseFragment fragment = new AddExerciseFragment();
        Bundle args = new Bundle();
        args.putInt("workoutId", workoutId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            // TODO: what if this is 0? What if args is null?
            mWorkoutId = args.getInt("workoutId", 0);
            Log.d("DEBUG", "AddExercise workoutId: " + mWorkoutId);
        }
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

                mPresenter.onAddButtonClick(exerciseName, reps, sets, restTime, notes, mWorkoutId);
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
        ViewWorkoutFragment viewWorkoutFragment = ViewWorkoutFragment.newInstance(mWorkoutId);
        ViewWorkoutPresenter viewWorkoutPresenter = new ViewWorkoutPresenter(
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
    public void setPresenter(Object presenter) {
        mPresenter = (AddExerciseContract.Presenter) presenter;
    }
}
