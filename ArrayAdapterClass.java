package com.example.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArrayAdapterClass extends ArrayAdapter<Card> {
    Context context;
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
        switch (card.getProfilePicURL()){
            case "defaultImage":
                Glide.with(getContext()).load(R.mipmap.ic_launcher).into(image);
                break;
            default://has image URL
                Glide.with(getContext()).load(card.getProfilePicURL()).into(image);
                break;
        }
        return convertView;
    }
}
