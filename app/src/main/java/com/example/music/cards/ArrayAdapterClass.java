package com.example.music.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.music.R;

import java.util.List;

public class ArrayAdapterClass extends ArrayAdapter<Card> {
    public ArrayAdapterClass(Context context, int resourceID, List<Card> items){
        super(context, resourceID, items);
    }
    public View getView(int position, View convertView, ViewGroup parent){

        Card card=getItem(position);
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView name = convertView.findViewById(R.id.name);
        ImageView image = convertView.findViewById(R.id.image);
        name.setText(card.getName());

        if (card.getProfilePicURL().equals("defaultImage"))
            Glide.with(getContext()).load(R.mipmap.ic_launcher).into(image);
        else //has image URL
            Glide.with(getContext()).load(card.getProfilePicURL()).into(image);

        return convertView;
    }
}