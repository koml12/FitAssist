package com.koml2.fitassist.viewworkoutlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.koml2.fitassist.R;
import com.koml2.fitassist.data.workout.Workout;

import java.util.List;

public class ViewWorkoutListAdapter extends RecyclerView.Adapter<ViewWorkoutListAdapter.ViewHolder> {

    private List<Workout> mWorkoutList;
    private Context mContext;
    private ViewWorkoutListFragment mView;

    public ViewWorkoutListAdapter(List<Workout> workouts, Context context, ViewWorkoutListFragment view) {
        setWorkoutList(workouts);
        mContext = context;
        mView = view;
    }

    public void setWorkoutList(List<Workout> workoutList) {
        mWorkoutList = workoutList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_item, parent, false);
        return new ViewHolder(view, mWorkoutList.get(i), mContext, mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Workout workout = mWorkoutList.get(i);

        TextView workoutNameTextView = viewHolder.getView().findViewById(R.id.tv_workout_name);
        workoutNameTextView.setText(workout.getName());

        viewHolder.setWorkout(workout);
    }

    @Override
    public int getItemCount() {
        return mWorkoutList.size();
    }

    public void updateWorkouts(List<Workout> workouts) {
        mWorkoutList = workouts;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private Workout mWorkout;
        private Context mContext;
        private ViewWorkoutListFragment mFragment;

        public ViewHolder(View view, Workout workout, Context context, ViewWorkoutListFragment fragment) {
            super(view);

            mView = view;
            mWorkout = workout;
            mContext = context;
            mFragment = fragment;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragment.toViewWorkout(mWorkout.getId());
                }
            });
        }

        public void setWorkout(Workout workout) {
            mWorkout = workout;
        }

        public View getView() {
            return mView;
        }
    }
}
