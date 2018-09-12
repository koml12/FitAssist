package com.koml2.fitassist.data.exercise;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import com.koml2.fitassist.data.workout.Workout;

/**
 * Represents an exercise model in the database.
 */
@Entity(tableName = "exercises", foreignKeys = @ForeignKey(
        entity = Workout.class,
        parentColumns = "_id",
        childColumns = "workoutId",
        onDelete = ForeignKey.CASCADE
))
public class Exercise {

    public Exercise() {
    }

    public Exercise(int id, int workoutId, String name, int sets, int reps, int restTime, String notes) {
        setId(id);
        setWorkoutId(workoutId);
        setName(name);
        setSets(sets);
        setReps(reps);
        setRestTime(restTime);
        setNotes(notes);
    }


    /**
     * Unique integer id for an exercise.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    @ColumnInfo(name = "workoutId")
    private int workoutId;

    /**
     * Name of the exercise. Can be an
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * Rep target for the exercise.
     */
    @ColumnInfo(name = "reps")
    private int reps;

    /**
     * Set target for the exercise.
     */
    @ColumnInfo(name = "sets")
    private int sets;

    /**
     * Rest time (in seconds) between sets.
     */
    @ColumnInfo(name = "restTime")
    private int restTime;

    /**
     * Miscellaneous notes on the exercise.
     */
    @ColumnInfo(name = "notes")
    private String notes;

    public static boolean equalTo(Exercise first, Exercise second) {
        return first.getId() == second.getId();
    }


    //////////////////////////////////////////////////////////
    // Getters and Setters ///////////////////////////////////
    //////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Exercise) {
            Exercise other = (Exercise) obj;
            return (id == other.getId() && name.equals(other.getName()) && reps == other.getReps() &&
                    sets == other.getSets() && restTime == other.getRestTime() && notes.equals(other.getNotes()));
        } else {
            return false;
        }
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
