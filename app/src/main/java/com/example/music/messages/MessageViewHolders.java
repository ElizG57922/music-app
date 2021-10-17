package com.example.music.messages;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;

public class MessageViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView message;
    public LinearLayout container;

    public MessageViewHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

        message=itemView.findViewById(R.id.message);
        container=itemView.findViewById(R.id.container);
    }

    @Override
    public void onClick(View view) {
    }
}