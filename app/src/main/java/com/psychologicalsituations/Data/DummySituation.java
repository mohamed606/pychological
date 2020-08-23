package com.psychologicalsituations.Data;

import java.util.Date;

public class DummySituation {
    private String situation;
    private Date situationDate;

    public DummySituation(String situation, Date situationDate) {
        this.situation = situation;
        this.situationDate = situationDate;
    }

    public String getSituation() {
        return situation;
    }

    public Date getSituationDate() {
        return situationDate;
    }
}
