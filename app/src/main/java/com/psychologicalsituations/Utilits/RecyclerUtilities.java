package com.psychologicalsituations.Utilits;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class RecyclerUtilities {
    public static void setUpRecycler(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.LayoutManager layoutManager, @NonNull RecyclerView.Adapter adapter){
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
