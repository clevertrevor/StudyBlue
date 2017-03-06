package com.trevor.studyblue.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trevor.studyblue.R;
import com.trevor.studyblue.adapter.ReposAdapter.RepoViewHolder;
import com.trevor.studyblue.model.Repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReposAdapter extends RecyclerView.Adapter<RepoViewHolder> {

    private List<Repo> repos = new ArrayList<>();
    private Activity activity;

    static class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView name, id, ownerLogin, updatedAt, language;
        ImageView image;

        RepoViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.item_name);
            id = (TextView) v.findViewById(R.id.item_id);
            ownerLogin = (TextView) v.findViewById(R.id.item_owner_login);
            updatedAt = (TextView) v.findViewById(R.id.item_updated_at);
            language = (TextView) v.findViewById(R.id.item_language);
            image = (ImageView) v.findViewById(R.id.item_image);
        }

        void setOnClickListener(OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
        }
    }

    public ReposAdapter(Activity context) {
        this.activity = context;
    }

    public void updateList(List<Repo> repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.list_repo_item, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RepoViewHolder holder, final int position) {
        final Repo repo = repos.get(position);
        holder.name.setText(repo.getName());
        holder.id.setText(String.format(Locale.getDefault(), "%d", repo.getId()));
        holder.ownerLogin.setText(repo.getOwner().getLogin());
        holder.updatedAt.setText(repo.getUpdatedAt());
        holder.language.setText(repo.getLanguage());
        holder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(repo.getCloneUrl()));
                activity.startActivity(intent);
            }
        });
        // avatar
        final String src = repo.getOwner().getAvatarUrl();
        Picasso.with(activity)
                .load(src)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

}