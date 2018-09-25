package com.koml2.fitassist.addworkout;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.viewworkoutlist.ViewWorkoutListFragment;
import com.koml2.fitassist.viewworkoutlist.ViewWorkoutListPresenter;


public class AddWorkoutFragment extends Fragment implements AddWorkoutContract.View {

    private AddWorkoutContract.Presenter mPresenter;
    private EditText mWorkoutNameEditText;
    private Button mAddWorkoutButton;

    public AddWorkoutFragment() {
        // Required empty public constructor
    }

    public static AddWorkoutFragment newInstance() {
        AddWorkoutFragment fragment = new AddWorkoutFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_workout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWorkoutNameEditText = view.findViewById(R.id.et_add_workout_workout_name);
        mAddWorkoutButton = view.findViewById(R.id.btn_add_workout_add_workout_button);

        mAddWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String workoutName = mWorkoutNameEditText.getText().toString();

                if (workoutName.equals("")) {
                    Snackbar snackbar = Snackbar.make(getView(), "Workout name field cannot be blank", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

                mPresenter.handleAddButtonClick(mWorkoutNameEditText.getText().toString());
            }
        });
    }

    @Override
    public void showAddWorkoutForm() {

    }

    @Override
    public void toViewWorkoutList() {
        ViewWorkoutListFragment fragment = ViewWorkoutListFragment.newInstance();
        ViewWorkoutListPresenter presenter = new ViewWorkoutListPresenter(
                FitAssistRepository.getInstance(getActivity().getApplicationContext()),
                fragment
        );

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        manager.popBackStackImmediate();
        transaction.commit();
    }

    @Override
    public void setPresenter(AddWorkoutPresenter presenter) {
        mPresenter = presenter;
    }
}
