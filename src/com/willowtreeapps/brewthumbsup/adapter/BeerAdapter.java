package com.willowtreeapps.brewthumbsup.adapter;

import com.willowtreeapps.brewthumbsup.R;
import com.willowtreeapps.brewthumbsup.api.model.Beer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * User: derek Date: 11/14/13 Time: 5:02 PM
 */
public class BeerAdapter extends BaseAdapter {

    private ArrayList<Beer> mBeers = new ArrayList<Beer>();
    private Context mContext;
    private LayoutInflater mInflater;
    private ViewHolder holder;

    public BeerAdapter(Context context, ArrayList<Beer> beers) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBeers = beers;
    }

    private class ViewHolder {

        public TextView name;
        public TextView breweryAbv;
        public TextView description;
    }

    @Override
    public int getCount() {
        return mBeers.size();
    }

    @Override
    public Beer getItem(int position) {
        return mBeers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.beer_list_item, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.breweryAbv = (TextView) view.findViewById(R.id.brewery_abv);
            holder.description = (TextView) view.findViewById(R.id.description);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Beer b = getItem(position);
        holder.name.setText(b.name);
        holder.breweryAbv.setText("Brewery: " + b.brewery.name + ", ABV: " + b.abv);
        holder.description.setText(b.description);
        return view;
    }
}
