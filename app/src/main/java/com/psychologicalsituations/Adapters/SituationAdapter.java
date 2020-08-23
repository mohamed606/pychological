package com.psychologicalsituations.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Data.DummySituation;
import com.psychologicalsituations.Holders.SituationHolder;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class SituationAdapter extends RecyclerView.Adapter<SituationHolder> {
    private List<DummySituation> situations;
    private Context context;
    private SituationClickListener situationClickListener;

    public SituationAdapter(List<DummySituation> situations, Context context, SituationClickListener situationClickListener) {
        this.situations = situations;
        this.context = context;
        this.situationClickListener = situationClickListener;
    }

    @NonNull
    @Override
    public SituationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.situation_item, parent, false);
        return new SituationHolder(view, situationClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SituationHolder holder, int position) {
        holder.getSituationTextView().setText(situations.get(position).getSituation());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String date = dateFormat.format(situations.get(position).getSituationDate());
        holder.getDateTextView().setText(date);
    }

    @Override
    public int getItemCount() {
        return situations.size();
    }
}
