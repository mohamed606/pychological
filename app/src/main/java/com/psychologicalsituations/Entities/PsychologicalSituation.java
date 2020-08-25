package com.psychologicalsituations.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.psychologicalsituations.Convertors.DateConverter;

import java.util.Date;

@Entity(tableName = "situation_table")
@TypeConverters(DateConverter.class)
public class PsychologicalSituation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String idea;
    private String emotion;
    private String behaviour;
    private String wrongThinking;
    private String ceont;
    private String ceontP;
    private double degreeOfBelief;
    private double psychologicalDegree;
    private String alternativeThought;
    private Date date;

    public PsychologicalSituation(String idea, String emotion, String behaviour, String wrongThinking, String ceont, String ceontP, double degreeOfBelief, double psychologicalDegree, String alternativeThought, Date date) {
        this.idea = idea;
        this.emotion = emotion;
        this.behaviour = behaviour;
        this.wrongThinking = wrongThinking;
        this.ceont = ceont;
        this.ceontP = ceontP;
        this.degreeOfBelief = degreeOfBelief;
        this.psychologicalDegree = psychologicalDegree;
        this.alternativeThought = alternativeThought;
        this.date = date;
    }

    public String getIdea() {
        return idea;
    }

    public String getEmotion() {
        return emotion;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public String getWrongThinking() {
        return wrongThinking;
    }

    public String getCeont() {
        return ceont;
    }

    public String getCeontP() {
        return ceontP;
    }

    public double getDegreeOfBelief() {
        return degreeOfBelief;
    }

    public double getPsychologicalDegree() {
        return psychologicalDegree;
    }

    public String getAlternativeThought() {
        return alternativeThought;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
