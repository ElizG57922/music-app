package com.example.music;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MatchHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView matchID;
    public MatchHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        matchID=itemView.findViewById(R.id.matchId);
    }

    @Override
    public void onClick(View view) {

    }
}
