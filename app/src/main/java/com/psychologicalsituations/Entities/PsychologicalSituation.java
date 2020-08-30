package com.psychologicalsituations.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.psychologicalsituations.Convertors.DateConverter;

import java.util.Date;

@Entity(tableName = "situation_table")
@TypeConverters(DateConverter.class)
public class PsychologicalSituation implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String situation;
    private String idea;
    private String emotion;
    private String behaviour;
    private String wrongThinking;
    private String ceont;
    private String ceontP;
    private double cognitiveContinuum;
    private String alternativeThought;
    private String newEmotion;
    private String newBehaviour;
    private double degreeOfBelief;
    private double degreeOfExecution;
    private Date date;

    public PsychologicalSituation(String situation, String idea, String emotion, String behaviour, String wrongThinking, String ceont, String ceontP,double cognitiveContinuum, String alternativeThought, String newEmotion, String newBehaviour,double degreeOfBelief , double degreeOfExecution, Date date) {
        this.situation = (situation!=null && !situation.trim().isEmpty())?situation : "";
        this.idea = (idea!=null && !idea.trim().isEmpty())?idea : "";
        this.emotion = (emotion!=null && !emotion.trim().isEmpty())?emotion : "";
        this.behaviour = (behaviour!=null && !behaviour.trim().isEmpty())?behaviour : "";
        this.wrongThinking = (wrongThinking!=null && !wrongThinking.trim().isEmpty())?wrongThinking : "";
        this.ceont = (ceont!=null && !ceont.trim().isEmpty())?ceont : "";
        this.ceontP = (ceontP!=null && !ceontP.trim().isEmpty())?ceontP : "";
        this.degreeOfBelief = degreeOfBelief;
        this.degreeOfExecution = degreeOfExecution;
        this.alternativeThought = (alternativeThought!=null && !alternativeThought.trim().isEmpty())?alternativeThought : "";
        this.date = date;
        this.cognitiveContinuum = cognitiveContinuum;
        this.newEmotion = (newEmotion!=null && !newEmotion.trim().isEmpty())?newEmotion : "";;
        this.newBehaviour = (newBehaviour!=null && !newBehaviour.trim().isEmpty())?newBehaviour : "";;
    }

    protected PsychologicalSituation(Parcel in) {
        id = in.readInt();
        situation = in.readString();
        idea = in.readString();
        emotion = in.readString();
        behaviour = in.readString();
        wrongThinking = in.readString();
        ceont = in.readString();
        ceontP = in.readString();
        degreeOfBelief = in.readDouble();
        degreeOfExecution = in.readDouble();
        alternativeThought = in.readString();
    }

    public static final Creator<PsychologicalSituation> CREATOR = new Creator<PsychologicalSituation>() {
        @Override
        public PsychologicalSituation createFromParcel(Parcel in) {
            return new PsychologicalSituation(in);
        }

        @Override
        public PsychologicalSituation[] newArray(int size) {
            return new PsychologicalSituation[size];
        }
    };

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

    public double getDegreeOfExecution() {
        return degreeOfExecution;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(situation);
        parcel.writeString(idea);
        parcel.writeString(emotion);
        parcel.writeString(behaviour);
        parcel.writeString(wrongThinking);
        parcel.writeString(ceont);
        parcel.writeString(ceontP);
        parcel.writeDouble(degreeOfBelief);
        parcel.writeDouble(degreeOfExecution);
        parcel.writeString(alternativeThought);
    }

    public double getCognitiveContinuum() {
        return cognitiveContinuum;
    }

    public String getNewEmotion() {
        return newEmotion;
    }

    public String getNewBehaviour() {
        return newBehaviour;
    }
}
