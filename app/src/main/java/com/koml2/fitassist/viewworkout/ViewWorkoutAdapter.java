package com.koml2.fitassist.viewworkout;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.FitAssistRepository;
import com.koml2.fitassist.data.exercise.Exercise;
import com.koml2.fitassist.editdeleteexercise.EditDeleteExerciseFragment;
import com.koml2.fitassist.editdeleteexercise.EditDeleteExercisePresenter;

import java.util.List;

public class ViewWorkoutAdapter extends RecyclerView.Adapter<ViewWorkoutAdapter.ViewHolder> {
    private List<Exercise> mExerciseList;
    private Context mContext;
    private ViewWorkoutFragment mView;

    public void setExerciseList(List<Exercise> exercises) {
        mExerciseList = exercises;
    }


    public ViewWorkoutAdapter(List<Exercise> exercises, Context context, ViewWorkoutFragment view) {
        setExerciseList(exercises);
        mContext = context;
        mView = view;
    }

    @Override
    public ViewWorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        return new ViewHolder(view, mExerciseList.get(i), mContext, mView);
    }

    @Override
    public void onBindViewHolder(ViewWorkoutAdapter.ViewHolder viewHolder, int position) {
        Exercise exercise = mExerciseList.get(position);

        // Get TextView's for setting exercise information.
        TextView exerciseNameTextView = viewHolder.mView.findViewById(R.id.tv_exercise_item_name);
        TextView repsSetsTextView = viewHolder.mView.findViewById(R.id.tv_exercise_item_reps_sets);
        TextView restTimeTextView = viewHolder.mView.findViewById(R.id.tv_exercise_item_rest);
        TextView notesTextView = viewHolder.mView.findViewById(R.id.tv_exercise_item_notes);

        // Construct reps x sets string.
        String repsSetsString = String.valueOf(exercise.getSets()) + "x" + String.valueOf(exercise.getSets());

        // Bind views to data from list.
        exerciseNameTextView.setText(exercise.getName());
        repsSetsTextView.setText(repsSetsString);
        restTimeTextView.setText(String.valueOf(exercise.getRestTime()));
        notesTextView.setText(exercise.getNotes());


        viewHolder.setExercise(mExerciseList.get(position));

    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    public void updateExerciseList(List<Exercise> exercises) {
        mExerciseList = exercises;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private ViewWorkoutFragment mFragment;
        private Context mContext;
        private Exercise mExercise;

        public void setExercise(Exercise exercise) {
            mExercise = exercise;
        }

        public ViewHolder(View view, Exercise exercise, Context context, ViewWorkoutFragment fragment) {
            super(view);
            mView = view;
            mFragment = fragment;
            mContext = context;
            mExercise = exercise;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = mExercise.getId();

                    Log.d("DEBUG", "exerciseID: " + id);

                    EditDeleteExerciseFragment fragment = EditDeleteExerciseFragment.newInstance(id);

                    EditDeleteExercisePresenter presenter =
                            new EditDeleteExercisePresenter(FitAssistRepository.getInstance(mContext), fragment);


                    FragmentManager manager = ((Activity) mContext).getFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.add(R.id.fragment_edit_delete_exercise_container, fragment).addToBackStack(null);
                    transaction.hide(mFragment);
                    transaction.commit();

                }
            });

        }
    }


}