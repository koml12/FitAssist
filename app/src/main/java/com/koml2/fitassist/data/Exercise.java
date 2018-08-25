package com.koml2.fitassist.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Represents an exercise model in the database.
 */
@Entity(tableName = "exercises")
public class Exercise {

    public Exercise() {
    }

    public Exercise(int id, String name, int sets, int reps, int restTime, String notes) {
        setId(id);
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
}
