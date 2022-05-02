package com.example.music.messages;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;

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
        View layoutView= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, null, false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(params);
        MessageViewHolders row = new MessageViewHolders(layoutView);
        return row;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolders holder, int position) {
        holder.message.setText(messageList.get(position).getMessage());
        if(messageList.get(position).isCurrentUser()){
            holder.message.setGravity(Gravity.END);
            holder.message.setBackgroundColor(Color.parseColor("#404040"));
            holder.container.setBackgroundColor(Color.parseColor("#2DB4CA"));
        }
        else{
            holder.message.setGravity(Gravity.START);
            holder.message.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.container.setBackgroundColor(Color.parseColor("#404040"));
        }
    }

    @Override
    public int getItemCount() {
        return this.messageList.size();
    }
}
