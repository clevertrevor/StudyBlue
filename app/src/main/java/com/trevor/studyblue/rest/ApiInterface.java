package com.trevor.studyblue.rest;

import com.trevor.studyblue.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("repos")
    Call<List<Repo>> getRepos();

}