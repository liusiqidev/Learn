package com.lsq.learn.retrofit.api;

import com.lsq.learn.retrofit.modle.Repo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lsq on 18-7-13.
 */

public interface Api {
    /**
     *https://api.github.com/users/rengwuxian/repos
     */
    @GET("/users/{user}/repos")
    Call<List<Repo>> getGithubUserRepo(@Path("user")String user);

}
