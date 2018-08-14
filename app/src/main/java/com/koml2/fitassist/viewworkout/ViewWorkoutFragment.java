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
import com.koml2.fitassist.data.Exercise;
import com.koml2.fitassist.data.ExerciseRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewWorkoutFragment} interface
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

        mPresenter.loadExercises();
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

    }

    @Override
    public void startAddExercise() {
        AddExerciseFragment addExerciseFragment = AddExerciseFragment.newInstance();

        AddExercisePresenter addExercisePresenter = new AddExercisePresenter(
                ExerciseRepository.getInstance(getActivity().getApplicationContext()),
                addExerciseFragment);

        FragmentManager manager = getActivity().getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_add_exercise_container, addExerciseFragment);
        transaction.detach(this);
        transaction.commit();

    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (ViewWorkoutContract.Presenter) presenter;
    }
}
