package com.koml2.fitassist.viewworkout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.Exercise;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewWorkoutFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewWorkoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewWorkoutFragment extends Fragment implements ViewWorkoutContract.View {

    private ViewWorkoutContract.Presenter mPresenter;

    private RecyclerView mExerciseRecyclerView;
    private ViewWorkoutAdapter mAdapter = null;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mAddExerciseButton;


    public ViewWorkoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ViewWorkoutFragment.
     */
    public static ViewWorkoutFragment newInstance() {
        return new ViewWorkoutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_workout, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mExerciseRecyclerView = (RecyclerView) getView().findViewById(R.id.rv_exercise_list);
        mAddExerciseButton = getView().findViewById(R.id.btn_exercise_list_add);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mExerciseRecyclerView.setLayoutManager(mLayoutManager);

        Log.d("DEBUG", "Layout manager set");

        mAddExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClick(view);
            }
        });

        mPresenter.loadExercises();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void refreshExercises() {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
            Log.d("DEBUG", "refreshExercises");
        }
    }

    @Override
    public void showExercises(List<Exercise> exercises) {
        if (mAdapter == null) {
            mAdapter = new ViewWorkoutAdapter(exercises);
            mExerciseRecyclerView.setAdapter(mAdapter);
            Log.d("DEBUG", "showExercises");

        } else {
            mAdapter.updateExerciseList(exercises);
            Log.d("DEBUG", "showExercises refresh");

        }
    }

    @Override
    public void onAddButtonClick(View view) {
        EditText mAddExerciseEditText = getView().findViewById(R.id.et_exercise_list);
        mPresenter.handleAddButtonClick(mAddExerciseEditText.getText().toString());
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (ViewWorkoutContract.Presenter) presenter;
    }
}
