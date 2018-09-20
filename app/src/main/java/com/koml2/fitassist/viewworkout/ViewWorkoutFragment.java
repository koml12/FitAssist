package com.koml2.fitassist.viewworkout;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.koml2.fitassist.R;
import com.koml2.fitassist.addexercise.AddExerciseFragment;
import com.koml2.fitassist.addexercise.AddExercisePresenter;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.data.exercise.Exercise;

import java.util.List;


public class ViewWorkoutFragment extends Fragment implements ViewWorkoutContract.View {

    private ViewWorkoutContract.Presenter mPresenter;

    private int mWorkoutId;

    private RecyclerView mExerciseRecyclerView;
    private ViewWorkoutAdapter mAdapter = null;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mAddExerciseButton;


    public ViewWorkoutFragment() {
        // Required empty public constructor
    }

    public static ViewWorkoutFragment newInstance(int workoutId) {
        ViewWorkoutFragment fragment =  new ViewWorkoutFragment();
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
            mWorkoutId = args.getInt("workoutId", 0);
            Log.d("DEBUG", "ViewWorkout workoutId: " + mWorkoutId);
        }
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
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mExerciseRecyclerView = view.findViewById(R.id.rv_view_workout_exercise_list);
        mAddExerciseButton = view.findViewById(R.id.btn_view_workout_add_exercise);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mExerciseRecyclerView.setLayoutManager(mLayoutManager);

        Log.d("DEBUG", "Layout manager set");

        mAddExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddExercise();
            }
        });

        mPresenter.loadExercises(mWorkoutId);
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
            mAdapter = new ViewWorkoutAdapter(exercises, getActivity(), this);
            mExerciseRecyclerView.setAdapter(mAdapter);
            Log.d("DEBUG", "showExercises");

        } else {
            mAdapter.updateExerciseList(exercises);
            Log.d("DEBUG", "showExercises refresh");

        }
    }

    @Override
    public void onAddButtonClick(View view) {

    }

    @Override
    public void startAddExercise() {
        AddExerciseFragment addExerciseFragment = AddExerciseFragment.newInstance(mWorkoutId);

        AddExercisePresenter addExercisePresenter = new AddExercisePresenter(
                FitAssistRepository.getInstance(getActivity().getApplicationContext()),
                addExerciseFragment);

        FragmentManager manager = getActivity().getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_add_exercise_container, addExerciseFragment).addToBackStack(null);
        transaction.hide(this);
        transaction.commit();

    }

    @Override
    public void setPresenter(ViewWorkoutPresenter presenter) {
        mPresenter = presenter;
    }
}
