package com.psychologicalsituations.Holders;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;
import com.psychologicalsituations.Utilits.DateUtilits;

import java.util.List;

public class SituationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView situationTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private SituationClickListener situationClickListener;
    private List<PsychologicalSituation> situationList;

    public SituationHolder(@NonNull View itemView, SituationClickListener situationClickListener, List<PsychologicalSituation> situationList) {
        super(itemView);
        situationTextView = itemView.findViewById(R.id.situation_textView);
        dateTextView = itemView.findViewById(R.id.date_textView);
        timeTextView = itemView.findViewById(R.id.time_textView);
        this.situationClickListener = situationClickListener;
        itemView.setOnClickListener(this);
        this.situationList = situationList;
    }


    public void bindData(PsychologicalSituation psychologicalSituation) {
        situationTextView.setText(psychologicalSituation.getSituation());
        String date = DateUtilits.fromatDate(psychologicalSituation.getDate());
        String [] dateSplinted = date.split(" ");
        dateTextView.setText(dateSplinted[0]);
        timeTextView.setText(dateSplinted[1]);
    }

    @Override
    public void onClick(View view) {
        if (RecyclerView.NO_POSITION != getAdapterPosition()) {
            PsychologicalSituation situation = situationList.get(getAdapterPosition());
            Log.v("date", String.valueOf(situation.getDate()));
            situationClickListener.goToDetailActivity(situation);
        }
    }
}
