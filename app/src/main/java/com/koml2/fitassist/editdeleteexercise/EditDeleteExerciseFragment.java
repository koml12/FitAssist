package com.koml2.fitassist.editdeleteexercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.Exercise;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditDeleteExerciseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditDeleteExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditDeleteExerciseFragment extends Fragment implements EditDeleteExerciseContract.View {

    /**
     * Presenter for the Fragment.
     */
    private EditDeleteExerciseContract.Presenter mPresenter;

    /**
     * The ID of the exercise that we want to update or delete.
     */
    private int mExerciseId;


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
    // TODO: Rename and change types and number of parameters
    public static EditDeleteExerciseFragment newInstance(int exerciseId) {
        EditDeleteExerciseFragment fragment = new EditDeleteExerciseFragment();
        Bundle args = new Bundle();
        args.putInt("ID", exerciseId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mExerciseId = bundle.getInt("ID", 0);
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

                mPresenter.handleUpdateClick(mExerciseId, name, reps, sets, restTime, notes);
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

                mPresenter.handleDeleteClick(mExerciseId, name, sets, reps, restTime, notes);
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

    @Override
    public void onUpdateClick(String name, String setsStr, String repsStr, String restTimeStr, String notes) {

    }

    @Override
    public void onDeleteClick(String name, String setsStr, String repsStr, String restTimeStr, String notes) {

    }

    @Override
    public void goToExerciseList() {

    }

    @Override
    public void setPresenter(EditDeleteExerciseContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
