package com.willowtreeapps.brewthumbsup.adapter;

import com.willowtreeapps.brewthumbsup.R;
import com.willowtreeapps.brewthumbsup.api.model.Movie;

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
public class MovieAdapter extends BaseAdapter {

    private ArrayList<Movie> mMovies = new ArrayList<Movie>();
    private Context mContext;
    private LayoutInflater mInflater;
    private ViewHolder holder;

    public MovieAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    private class ViewHolder {

        public TextView title;
        public TextView ratingRunningTime;
        public TextView synopsis;
        public TextView criticScore;
        public TextView audienceScore;
        public TextView criticsConsensus;
    }

    public void setData(ArrayList<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.movie_list_item, parent, false);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.ratingRunningTime = (TextView) view.findViewById(R.id.rating_running_time);
            holder.synopsis = (TextView) view.findViewById(R.id.synopsis);
            holder.criticScore = (TextView) view.findViewById(R.id.critic_score);
            holder.audienceScore = (TextView) view.findViewById(R.id.audience_score);
            holder.criticsConsensus = (TextView) view.findViewById(R.id.critics_consensus);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Movie m = getItem(position);
        holder.title.setText(m.title);
        holder.ratingRunningTime.setText("Rated: " + m.mpaaRating + ", Running Time: " + m.runtime + " min");
        holder.synopsis.setText(m.synopsis);
        holder.criticScore.setText("Critics Score: " + m.ratings.criticsScore);
        holder.audienceScore.setText("Audience Score: " + m.ratings.audienceScore);
        holder.criticsConsensus.setText("Critics Consensus: " + m.criticsConsensus);
        return view;
    }
}
