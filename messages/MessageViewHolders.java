package com.example.music.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.messages.MessageActivity;

public class MessageViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

    public MessageViewHolders(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
