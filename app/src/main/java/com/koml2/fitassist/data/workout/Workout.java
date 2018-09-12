package com.koml2.fitassist.data.workout;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Basic workout model. Exercises can reference this model.
 * A workout can be linked from numerous exercises, but each exercise can have only one workout.
 */
@Entity(tableName = "workouts")
public class Workout {

    /**
     * Unique integer ID for the workout.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    private int id;

    /**
     * Name for the workout.
     */
    @ColumnInfo(name = "name")
    private String name;

    /**
     * Empty constructor if we want to set fields manually.
     */
    public Workout() {
    }

    /**
     * Constructor that only sets the name, and autogenerates an id.
     *
     * @param name      name of the workout.
     */
    public Workout(String name) {
        setName(name);
    }

    /**
     * Constructor that sets both name and id. Used when referencing workouts that are already created.
     *
     * @param id            ID of the workout.
     * @param name          name of the workout.
     */
    public Workout(int id, String name) {
        setId(id);
        setName(name);
    }

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
}
