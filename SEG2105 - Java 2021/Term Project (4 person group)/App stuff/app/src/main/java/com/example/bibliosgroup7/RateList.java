package com.example.bibliosgroup7;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RateList extends ArrayAdapter<String>{
    private Activity context;
    List<String> ratings;

    public RateList(Activity context, List<String> ratings) {
        super(context, R.layout.layout_account_list, ratings);
        this.context = context;
        this.ratings = ratings;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_account_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);

        String rating = ratings.get(position);
        String not = rating.substring(rating.indexOf(","), rating.length()-1);
        textViewName.setText(rating.substring(0, rating.indexOf(",")));
        textViewPrice.setText(not.substring(not.indexOf(",")+1,not.indexOf(":")));
        return listViewItem;
    }
}
