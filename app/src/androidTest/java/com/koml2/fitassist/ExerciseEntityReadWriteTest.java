package com.koml2.fitassist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.koml2.fitassist.data.FitAssistDatabase;
import com.koml2.fitassist.data.exercise.Exercise;
import com.koml2.fitassist.data.exercise.ExerciseDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExerciseEntityReadWriteTest {

    private ExerciseDao mExerciseDao;
    private FitAssistDatabase mFitAssistDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mFitAssistDatabase = Room.inMemoryDatabaseBuilder(context, FitAssistDatabase.class).build();
        mExerciseDao = mFitAssistDatabase.getExerciseDao();
    }

    @After
    public void destroyDb() {
        mFitAssistDatabase.close();
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
