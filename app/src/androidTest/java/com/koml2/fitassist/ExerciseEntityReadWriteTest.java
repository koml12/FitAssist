package com.koml2.fitassist;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.koml2.fitassist.data.Exercise;
import com.koml2.fitassist.data.ExerciseDao;
import com.koml2.fitassist.data.ExerciseDatabase;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

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
    public void destroyDb() throws IOException {
        mExerciseDatabase.close();
    }

    @Test
    public void writeExerciseAndReadInList() throws Exception {
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

    @Test
    public void flowableHasCorrectData() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setId(1);
        exercise.setName("bench press");
        exercise.setReps(5);
        exercise.setSets(5);
        exercise.setRestTime(90);
        exercise.setNotes("");

        List<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise);


        mExerciseDao.insertExercise(exercise);

        Flowable<List<Exercise>> exerciseFlowable = mExerciseDao.getAllExercises();

        List<Exercise> testExercises = exerciseFlowable.toList().blockingGet().get(0);

        Assert.assertEquals(exercise, testExercises.get(0));


    }

}
