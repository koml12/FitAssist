package com.koml2.fitassist.data.workout;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface WorkoutDao {

    @Insert
    void insertWorkout(Workout workout);

    @Update
    void updateWorkout(Workout workout);

    @Delete
    void deleteWorkout(Workout workout);

    @Query("SELECT * FROM workouts")
    List<Workout> getAllWorkouts();

    @Query("SELECT * FROM workouts WHERE _id = :id LIMIT 1")
    Workout getWorkoutById(int id);
}
