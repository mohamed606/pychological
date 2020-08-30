package com.psychologicalsituations.Holders;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;
import com.psychologicalsituations.Utilits.DateUtilities;

import java.util.List;

public class SituationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView situationTextView;
    private TextView dateTextView;
    private TextView timeTextView;
    private SituationClickListener situationClickListener;
    private List<PsychologicalSituation> situationList;
    private Context context;
    public SituationHolder(@NonNull View itemView, SituationClickListener situationClickListener, List<PsychologicalSituation> situationList, Context context) {
        super(itemView);
        situationTextView = itemView.findViewById(R.id.situation_textView);
        dateTextView = itemView.findViewById(R.id.date_textView);
        timeTextView = itemView.findViewById(R.id.time_textView);
        this.situationClickListener = situationClickListener;
        itemView.setOnClickListener(this);
        this.situationList = situationList;
        this.context = context;
    }


    public void bindData(PsychologicalSituation psychologicalSituation) {
        Log.v("shit","target");
        situationTextView.setText(psychologicalSituation.getSituation());
        String date = DateUtilities.formatDate(psychologicalSituation.getDate(),context.getResources().getConfiguration().locale);
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
