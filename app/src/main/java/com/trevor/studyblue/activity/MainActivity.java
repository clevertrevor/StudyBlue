package com.trevor.studyblue.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final ReposAdapter adapter = new ReposAdapter(this);
        recyclerView.setAdapter(adapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Repo>> call = apiService.getRepos();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> repos = response.body();
                adapter.updateList(repos);
                String text = "Retrieved " + repos.size() + " repos.";
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Toast.makeText(context, "Failed to retrieve info", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
