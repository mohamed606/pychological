package com.psychologicalsituations.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Holders.SituationHolder;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class SituationAdapter extends RecyclerView.Adapter<SituationHolder> {
    private List<PsychologicalSituation> situations = new ArrayList<>();
    private SituationClickListener situationClickListener;
    private DateFormat simpleDateFormat;

    public SituationAdapter(SituationClickListener situationClickListener, DateFormat simpleDateFormat) {
        this.situationClickListener = situationClickListener;
        this.simpleDateFormat = simpleDateFormat;
    }

    public void setSituations(List<PsychologicalSituation> situations) {
        this.situations = situations;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SituationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.situation_item, parent, false);
        return new SituationHolder(view, situationClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SituationHolder holder, int position) {
        PsychologicalSituation situation = situations.get(position);
        String date = simpleDateFormat.format(situation.getDate());
        holder.getSituationTextView().setText(situation.getSituation());
        holder.getDateTextView().setText(date);
    }

    @Override
    public int getItemCount() {
        return situations.size();
    }

    public PsychologicalSituation getSituation(int position) {
        return situations.get(position);
    }
}
