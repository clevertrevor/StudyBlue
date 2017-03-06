package com.trevor.studyblue.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.trevor.studyblue.R;
import com.trevor.studyblue.adapter.ReposAdapter;
import com.trevor.studyblue.model.Repo;
import com.trevor.studyblue.rest.ApiClient;
import com.trevor.studyblue.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private ReposAdapter adapter;
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReposAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiInterface apiService = ApiClient.getClient(context).create(ApiInterface.class);
        Call<List<Repo>> call = apiService.getRepos();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                recyclerView.setVisibility(View.VISIBLE);
                Log.d(TAG, "Successfully retrieved repos.");
                List<Repo> repos = response.body();
                adapter.updateList(repos);
                Toast.makeText(context, R.string.success_message, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                recyclerView.setVisibility(View.INVISIBLE);
                Log.d(TAG, "Failed to retrieve repos.");
                Toast.makeText(context, R.string.fail_message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}