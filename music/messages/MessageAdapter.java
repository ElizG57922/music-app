package com.example.music.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.music.R;
import com.example.music.connections.MatchViewHolders;
import com.example.music.connections.Matches;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolders> {
    private List<Message> messageList;
    private Context context;

    public MessageAdapter(List<Message> messageList, Context context){
        this.messageList=messageList;
        this.context=context;
    }

    @NonNull
    @Override
    public MessageViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutView.setLayoutParams(params);
        MessageViewHolders row = new MessageViewHolders(layoutView);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolders holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
