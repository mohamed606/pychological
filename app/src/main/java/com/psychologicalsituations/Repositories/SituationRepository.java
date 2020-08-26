package com.psychologicalsituations.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.psychologicalsituations.DAO.SituationDao;
import com.psychologicalsituations.Databases.SituationDatabase;
import com.psychologicalsituations.Entities.PsychologicalSituation;

import java.util.List;

public class SituationRepository {
    private SituationDatabase situationDatabase;
    private SituationDao situationDao;
    private LiveData<List<PsychologicalSituation>> situations;

    public SituationRepository(Application application) {
        situationDatabase = SituationDatabase.getInstance(application);
        situationDao = situationDatabase.situationDao();
        situations = situationDao.getAllSituations();
    }

    public void insert(final PsychologicalSituation psychologicalSituation) {
        SituationDatabase.databaseWriteExecutor.execute(() -> {
            situationDao.insert(psychologicalSituation);
        });
    }

    public void update(final PsychologicalSituation psychologicalSituation) {
        SituationDatabase.databaseWriteExecutor.execute(() -> {
            situationDao.update(psychologicalSituation);
        });
    }

    public void delete(final PsychologicalSituation psychologicalSituation) {
        SituationDatabase.databaseWriteExecutor.execute(() -> {
            situationDao.delete(psychologicalSituation);
        });
    }

    public void deleteAll() {
        SituationDatabase.databaseWriteExecutor.execute(() -> {
            situationDao.deleteAll();
        });
    }

    public LiveData<List<PsychologicalSituation>> getAllSituations() {
        return situations;
    }
}
