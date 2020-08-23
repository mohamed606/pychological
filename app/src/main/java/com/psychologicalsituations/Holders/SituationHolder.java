package com.psychologicalsituations.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

public class SituationHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView situationTextView;
    private TextView dateTextView;
    private SituationClickListener situationClickListener;

    public SituationHolder(@NonNull View itemView, SituationClickListener situationClickListener) {
        super(itemView);
        situationTextView = itemView.findViewById(R.id.situation_textView);
        dateTextView = itemView.findViewById(R.id.date_textView);
        this.situationClickListener = situationClickListener;
        itemView.setOnClickListener(this);
    }

    public TextView getSituationTextView() {
        return situationTextView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    @Override
    public void onClick(View view) {
        situationClickListener.goToDetailActivity(getAdapterPosition());
    }
}
