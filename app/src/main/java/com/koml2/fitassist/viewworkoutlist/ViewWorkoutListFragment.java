package com.koml2.fitassist.viewworkoutlist;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.workout.Workout;

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
        // TODO: implement this when the "add workout" component is added.
    }

    @Override
    public void setPresenter(ViewWorkoutListPresenter presenter) {
        mPresenter = presenter;
    }
}
