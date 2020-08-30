package com.psychologicalsituations.Holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Listeners.SituationDetailClickListener;
import com.psychologicalsituations.R;

public class SituationDetailHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView titleTextView;
    private TextView detailTextView;
    private SituationDetailClickListener situationDetailClickListener;
    public SituationDetailHolder(@NonNull View itemView,SituationDetailClickListener situationDetailClickListener) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.title_tv);
        detailTextView = itemView.findViewById(R.id.details_tv);
        this.situationDetailClickListener = situationDetailClickListener;
        itemView.setOnClickListener(this);
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public TextView getDetailTextView() {
        return detailTextView;
    }

    @Override
    public void onClick(View view) {
        situationDetailClickListener.goToEditActivity(getAdapterPosition());
    }
}
