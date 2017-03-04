package com.trevor.studyblue.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trevor.studyblue.R;
import com.trevor.studyblue.adapter.ReposAdapter.RepoViewHolder;
import com.trevor.studyblue.model.Repo;

import java.util.ArrayList;
import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private List<Repo> repos = new ArrayList<>();
    private Context context;

    static class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        RepoViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
        }
    }

    public ReposAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<Repo> repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_repo_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, final int position) {
        holder.name.setText(repos.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }
}