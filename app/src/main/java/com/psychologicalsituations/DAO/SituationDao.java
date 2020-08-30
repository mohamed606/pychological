package com.psychologicalsituations.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.psychologicalsituations.Entities.PsychologicalSituation;

import java.util.List;

@Dao
public interface SituationDao {

    @Insert
    void insert(PsychologicalSituation psychologicalSituation);

    @Update
    void update(PsychologicalSituation psychologicalSituation);

    @Delete
    void delete(PsychologicalSituation psychologicalSituation);

    @Query("DELETE FROM situation_table")
    void deleteAll();

    @Query("SELECT * FROM situation_table ORDER BY date DESC")
    LiveData<List<PsychologicalSituation>> getAllSituations();
}
