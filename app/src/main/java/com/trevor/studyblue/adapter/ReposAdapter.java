package com.trevor.studyblue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.trevor.studyblue.R;
import com.trevor.studyblue.adapter.ReposAdapter.RepoViewHolder;
import com.trevor.studyblue.model.Repo;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private List<Repo> repos;
    private int rowLayout;
    private Context context;


    public static class RepoViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;


        public RepoViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);
        }
    }

    public ReposAdapter(List<Repo> repos, int rowLayout, Context context) {
        this.repos = repos;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new RepoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RepoViewHolder holder, final int position) {
        /*holder.movieTitle.setText(repos.get(position).getTitle());
        holder.data.setText(repos.get(position).getReleaseDate());
        holder.movieDescription.setText(repos.get(position).getOverview());
        holder.rating.setText(repos.get(position).getVoteAverage().toString()); */
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}