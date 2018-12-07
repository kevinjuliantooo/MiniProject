package com.example.piiik98.tokopediaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SourceAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> name;
    private ArrayList<String> description;
    private ArrayList<String> category;
    private ArrayList<String> id;
    LayoutInflater inflater;



    public SourceAdapter(Context mContext, ArrayList<String> name, ArrayList<String> description, ArrayList<String> category, ArrayList<String> id){
        this.mContext = mContext;
        this.name = name;
        this.description = description;
        this.category = category;
        this.id = id;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.news_source_adapter, null);
        TextView nameText = view.findViewById(R.id.nameText);
        TextView desciptionText = view.findViewById(R.id.descriptionText);
        TextView categoryText = view.findViewById(R.id.categoryText);

        //** Update UI **//

        nameText.setText(name.get(i));
        desciptionText.setText(description.get(i));
        categoryText.setText("Category: " +category.get(i));
        return view;
    }
}
