package com.koml2.fitassist.viewworkoutlist;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.koml2.fitassist.R;
import com.koml2.fitassist.addworkout.AddWorkoutFragment;
import com.koml2.fitassist.addworkout.AddWorkoutPresenter;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.data.workout.Workout;
import com.koml2.fitassist.viewworkout.ViewWorkoutFragment;
import com.koml2.fitassist.viewworkout.ViewWorkoutPresenter;

import java.util.List;

public class ViewWorkoutListFragment extends Fragment implements ViewWorkoutListContract.View {

    private ViewWorkoutListContract.Presenter mPresenter;

    private ViewWorkoutListAdapter mAdapter = null;
    private RecyclerView mWorkoutRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mAddWorkoutButton;

    public ViewWorkoutListFragment() {
        // Required empty public constructor
    }


    public static ViewWorkoutListFragment newInstance() {
        return new ViewWorkoutListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_workout_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWorkoutRecyclerView = view.findViewById(R.id.rv_workout_list);
        mAddWorkoutButton = view.findViewById(R.id.btn_view_workout_list_add_workout_fab);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mWorkoutRecyclerView.setLayoutManager(mLayoutManager);

        Log.d("DEBUG", "Workout layout manager set");

        mAddWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendToAddWorkout();
            }
        });

        mPresenter.loadWorkouts();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("DEBUG", "ViewWorkoutList onResume");

        mPresenter.loadWorkouts();


    }

    @Override
    public void showWorkouts(List<Workout> workouts) {
        if (mAdapter == null) {
            mAdapter = new ViewWorkoutListAdapter(workouts, getContext(), this);
            mWorkoutRecyclerView.setAdapter(mAdapter);
            Log.d("DEBUG", "showWorkouts");
        } else {
            mAdapter.updateWorkouts(workouts);
            Log.d("DEBUG", "showWorkouts refresh");
        }
    }

    @Override
    public void sendToAddWorkout() {

        mAdapter = null;

        AddWorkoutFragment fragment = AddWorkoutFragment.newInstance();
        AddWorkoutPresenter presenter = new AddWorkoutPresenter(
                FitAssistRepository.getInstance(getActivity().getApplicationContext()),
                fragment);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(this);

        //TODO
        //transaction.replace(R.id.fragment_add_workout_container, fragment);
        transaction.replace(R.id.fragment_container, fragment);

        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void toViewWorkout(int workoutId) {

        mAdapter = null;    // Force refresh when back is pressed in the ViewWorkout fragment.

        ViewWorkoutFragment fragment = ViewWorkoutFragment.newInstance(workoutId);
        ViewWorkoutPresenter presenter = new ViewWorkoutPresenter(
                FitAssistRepository.getInstance(getActivity().getApplicationContext()),
                fragment
        );

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.hide(this);


        //TODO
        //transaction.replace(R.id.fragment_view_workout_container, fragment);
        transaction.replace(R.id.fragment_container, fragment);


        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void setPresenter(ViewWorkoutListPresenter presenter) {
        mPresenter = presenter;
    }
}
