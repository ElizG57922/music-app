package com.example.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchHolders> {
    private List<Matches> matchesList;
    private Context context;

    public MatchesAdapter(List<Matches> matchesList, Context context){
        this.matchesList=matchesList;
        this.context=context;
    }

    @NonNull
    @Override
    public MatchHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutView.setLayoutParams(params);
        MatchHolders row = new MatchHolders(layoutView);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchHolders holder, int position) {
        holder.matchID.setText(matchesList.get(position).getUserID());
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
