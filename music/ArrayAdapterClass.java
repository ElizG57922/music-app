package com.example.music;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView image = (ImageView) convertView.findViewById(R.id.image);
        name.setText(card.getName());
        image.setImageResource(R.mipmap.ic_launcher);
        return convertView;
    }
}
