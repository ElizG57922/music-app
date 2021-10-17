package com.example.music.connections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.R;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchViewHolders> {
    private List<Matches> matchesList;
    private Context context;

    public MatchesAdapter(List<Matches> matchesList, Context context){
        this.matchesList=matchesList;
        this.context=context;
    }

    @NonNull
    @Override
    public MatchViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutView.setLayoutParams(params);
        MatchViewHolders row = new MatchViewHolders(layoutView);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolders holder, int position) {
        holder.connectionID.setText(matchesList.get(position).getUserID());
        holder.connectionName.setText(matchesList.get(position).getName());
        if(!matchesList.get(position).getProfilePicURL().equals("defaultImage")) {
            Glide.with(context).load(matchesList.get(position).getProfilePicURL()).into(holder.connectionImage);
        }
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}