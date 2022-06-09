package com.example.proyecto_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyecto_final.R;
import com.example.proyecto_final.models.Hero;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Hero> {

    private List<Hero> heroList;
    private Context mCtx;


    public ListViewAdapter(List <Hero> heroList, Context mCtx){
        super(mCtx, R.layout.list_items, heroList );
        this.heroList = heroList;
        this.mCtx = mCtx;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        TextView textViewName  = listViewItem.findViewById(R.id.textViewName);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.textViewImageUrl);

        Hero hero = heroList .get(position);

        textViewName.setText(hero.getName());
        textViewImageUrl.setText(hero.getImageUrl());


        return listViewItem;
    }
}
