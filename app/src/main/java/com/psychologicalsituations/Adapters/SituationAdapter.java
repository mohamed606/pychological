package com.psychologicalsituations.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.psychologicalsituations.Entities.PsychologicalSituation;
import com.psychologicalsituations.Holders.SituationHolder;
import com.psychologicalsituations.Listeners.SituationClickListener;
import com.psychologicalsituations.R;

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
                    oldItem.getDegreeOfExecution() == newItem.getDegreeOfExecution() &&
                    oldItem.getAlternativeThought().equals(newItem.getAlternativeThought());
        }
    };
    private SituationClickListener situationClickListener;

    public SituationAdapter(SituationClickListener situationClickListener) {
        super(DIFF_CALLBACK);
        this.situationClickListener = situationClickListener;
    }


    @NonNull
    @Override
    public SituationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.situation_item, parent, false);
        return new SituationHolder(view, situationClickListener, getCurrentList(),parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull SituationHolder holder, int position) {
        PsychologicalSituation situation = getItem(position);
        holder.bindData(situation);
    }

    public PsychologicalSituation getSituation(int position) {
        return getItem(position);
    }
}
