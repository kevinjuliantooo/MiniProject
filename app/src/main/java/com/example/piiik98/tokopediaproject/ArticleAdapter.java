package com.example.piiik98.tokopediaproject;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ArticleAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<String> title;
    private ArrayList<String> description;
    private ArrayList<String> url;
    private ArrayList<String> urltoImage;
    private LayoutInflater inflater;

    public ArticleAdapter(Context mContext, ArrayList<String> title, ArrayList<String> description, ArrayList<String> url, ArrayList<String> urltoImage){
        this.mContext = mContext;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urltoImage = urltoImage;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return title.size();
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
        view = inflater.inflate(R.layout.news_article_adapter, null);
        TextView titleText = view.findViewById(R.id.titleText);
        TextView descriptionText = view.findViewById(R.id.descriptionText);
        ImageView urlImage = view.findViewById(R.id.urlImage);


        //************************ Update UI ************************//

        titleText.setText(title.get(i));
        descriptionText.setText(description.get(i));

        if (urltoImage.get(i).isEmpty()) { //Null Image Cause Crash, so I did if-else Condition
            //Let it Go~
        } else {
            Picasso.get().load(urltoImage.get(i)).into(urlImage);
        }

        return view;
    }
}
