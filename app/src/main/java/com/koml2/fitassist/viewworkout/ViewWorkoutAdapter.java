package com.koml2.fitassist.viewworkout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.Exercise;

import java.util.List;

public class ViewWorkoutAdapter extends RecyclerView.Adapter<ViewWorkoutAdapter.ViewHolder> {
    private List<Exercise> mExerciseList;

    public void setExerciseList(List<Exercise> exercises) {
        mExerciseList = exercises;
    }


    public ViewWorkoutAdapter(List<Exercise> exercises) {
        mExerciseList = exercises;
    }

    @Override
    public ViewWorkoutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        return new ViewHolder(view);
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
        public View mView;
        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}