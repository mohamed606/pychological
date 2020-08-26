package com.psychologicalsituations.Databases;

import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.psychologicalsituations.DAO.SituationDao;
import com.psychologicalsituations.Entities.PsychologicalSituation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = PsychologicalSituation.class, version = 1)
public abstract class SituationDatabase extends RoomDatabase {
    private static SituationDatabase instance;

    public abstract SituationDao situationDao();

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static synchronized SituationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SituationDatabase.class, "situation_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
