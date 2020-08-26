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
    private String situation;
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

    public PsychologicalSituation(String situation,String idea, String emotion, String behaviour, String wrongThinking, String ceont, String ceontP, double degreeOfBelief, double psychologicalDegree, String alternativeThought, Date date) {
        this.situation = (situation!=null && !situation.trim().isEmpty())?situation : "";
        this.idea = (idea!=null && !idea.trim().isEmpty())?idea : "";
        this.emotion = (emotion!=null && !emotion.trim().isEmpty())?emotion : "";
        this.behaviour = (behaviour!=null && !behaviour.trim().isEmpty())?behaviour : "";
        this.wrongThinking = (wrongThinking!=null && !wrongThinking.trim().isEmpty())?wrongThinking : "";
        this.ceont = (ceont!=null && !ceont.trim().isEmpty())?ceont : "";
        this.ceontP = (ceontP!=null && !ceontP.trim().isEmpty())?ceontP : "";
        this.degreeOfBelief = degreeOfBelief;
        this.psychologicalDegree = psychologicalDegree;
        this.alternativeThought = (alternativeThought!=null && !alternativeThought.trim().isEmpty())?alternativeThought : "";
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

    public String getSituation() {
        return situation;
    }

}
