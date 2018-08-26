package com.koml2.fitassist.data;

import android.arch.persistence.room.*;

import java.util.List;

/**
 * Defines methods for accessing data from the database.
 */
@Dao
public interface ExerciseDao {

    @Insert
    void insertExercise(Exercise exercise);

    @Update
    void updateExercise(Exercise exercise);

    @Delete
    void deleteExercise(Exercise exercise);

    @Query("SELECT * FROM exercises")
    List<Exercise> getAllExercises();

    @Query("SELECT * FROM exercises WHERE _id = :id")
    Exercise getExerciseById(int id);
}
