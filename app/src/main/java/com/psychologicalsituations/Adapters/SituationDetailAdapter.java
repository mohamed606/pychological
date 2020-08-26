package com.psychologicalsituations.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Holders.SituationDetailHolder;
import com.psychologicalsituations.Listeners.SituationDetailClickListener;
import com.psychologicalsituations.R;

import java.util.List;

public class SituationDetailAdapter extends RecyclerView.Adapter<SituationDetailHolder> {
    private List<String> titles;
    private List<String> details;
    private int numberOfTitles;
    private Context context;
    private SituationDetailClickListener situationDetailClickListener;

    public SituationDetailAdapter(List<String> titles, List<String> details, int numberOfTitles, Context context,SituationDetailClickListener situationDetailClickListener) {
        this.titles = titles;
        this.details = details;
        this.numberOfTitles = numberOfTitles;
        this.context = context;
        this.situationDetailClickListener = situationDetailClickListener;
    }

    @NonNull
    @Override
    public SituationDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.situation_card,parent,false);
        return new SituationDetailHolder(view,situationDetailClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SituationDetailHolder holder, int position) {
        holder.getTitleTextView().setText(titles.get(position));
        holder.getDetailTextView().setText(details.get(position));
    }

    @Override
    public int getItemCount() {
        return numberOfTitles;
    }
}
