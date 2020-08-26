package com.psychologicalsituations.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Holders.SituationHolder;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class SituationAdapter extends ListAdapter<PsychologicalSituation, SituationHolder> {
    private static final DiffUtil.ItemCallback<PsychologicalSituation> DIFF_CALLBACK = new DiffUtil.ItemCallback<PsychologicalSituation>() {
        @Override
        public boolean areItemsTheSame(@NonNull PsychologicalSituation oldItem, @NonNull PsychologicalSituation newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PsychologicalSituation oldItem, @NonNull PsychologicalSituation newItem) {
            return oldItem.getIdea().equals(newItem.getIdea()) &&
                    oldItem.getSituation().equals(newItem.getSituation()) &&
                    oldItem.getEmotion().equals(newItem.getEmotion()) &&
                    oldItem.getBehaviour().equals(newItem.getBehaviour()) &&
                    oldItem.getWrongThinking().equals(newItem.getWrongThinking()) &&
                    oldItem.getCeont().equals(newItem.getCeont()) &&
                    oldItem.getCeontP().equals(newItem.getCeontP()) &&
                    oldItem.getDegreeOfBelief() == newItem.getDegreeOfBelief() &&
                    oldItem.getPsychologicalDegree() == newItem.getPsychologicalDegree() &&
                    oldItem.getAlternativeThought().equals(newItem.getAlternativeThought());
        }
    };
    private SituationClickListener situationClickListener;
    private DateFormat simpleDateFormat;

    public SituationAdapter(SituationClickListener situationClickListener, DateFormat simpleDateFormat) {
        super(DIFF_CALLBACK);
        this.situationClickListener = situationClickListener;
        this.simpleDateFormat = simpleDateFormat;
    }


    @NonNull
    @Override
    public SituationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.situation_item, parent, false);
        return new SituationHolder(view, situationClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SituationHolder holder, int position) {
        PsychologicalSituation situation =getItem(position);
        String date = simpleDateFormat.format(situation.getDate());
        holder.getSituationTextView().setText(situation.getSituation());
        holder.getDateTextView().setText(date);
    }

    public PsychologicalSituation getSituation(int position) {
        return getItem(position);
    }
}
