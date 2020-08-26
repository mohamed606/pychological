package com.psychologicalsituations.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Repositories.SituationRepository;

import java.util.List;

public class SituationViewModel extends AndroidViewModel {
    private SituationRepository situationRepository;
    private LiveData<List<PsychologicalSituation>> situations;

    public SituationViewModel(@NonNull Application application) {
        super(application);
        situationRepository = new SituationRepository(application);
        situations = situationRepository.getAllSituations();
    }

    public void insert(PsychologicalSituation psychologicalSituation) {
        situationRepository.insert(psychologicalSituation);
    }

    public void update(PsychologicalSituation psychologicalSituation) {
        situationRepository.update(psychologicalSituation);
    }

    public void delete(PsychologicalSituation psychologicalSituation) {
        situationRepository.delete(psychologicalSituation);
    }

    public void deleteAll() {
        situationRepository.deleteAll();
    }

    public LiveData<List<PsychologicalSituation>> getAllSituations() {
        return situations;
    }
}
