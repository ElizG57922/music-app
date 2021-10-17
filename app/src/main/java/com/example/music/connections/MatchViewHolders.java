package com.example.music.connections;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.messages.MessageActivity;
import com.example.music.R;

public class MatchViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView connectionID, connectionName;
    public ImageView connectionImage;

    public MatchViewHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        connectionID=itemView.findViewById(R.id.connectionId);
        connectionName=itemView.findViewById(R.id.connectionName);
        connectionImage=itemView.findViewById(R.id.connectionImage);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), MessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("connectionID", connectionID.getText().toString());
        intent.putExtras(bundle);
        view.getContext().startActivity(intent);
    }
}
