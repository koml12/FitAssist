package com.koml2.fitassist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.koml2.fitassist.data.Exercise;
import com.koml2.fitassist.data.ExerciseDao;
import com.koml2.fitassist.data.ExerciseDatabase;
import io.reactivex.Flowable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ExerciseEntityReadWriteTest {

    private ExerciseDao mExerciseDao;
    private ExerciseDatabase mExerciseDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mExerciseDatabase = Room.inMemoryDatabaseBuilder(context, ExerciseDatabase.class).build();
        mExerciseDao = mExerciseDatabase.getExerciseDao();
    }

    @After
    public void destroyDb() {
        mExerciseDatabase.close();
    }

    @Test
    public void writeExerciseAndReadInList() {
        Exercise exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("bench press");
        exercise.setReps(5);
        exercise.setSets(5);
        exercise.setRestTime(90);
        exercise.setNotes("");

        mExerciseDao.insertExercise(exercise);
        Exercise gotExercise = mExerciseDao.getExerciseById(1);
        Assert.assertTrue(Exercise.equalTo(exercise, gotExercise));
    }

}
