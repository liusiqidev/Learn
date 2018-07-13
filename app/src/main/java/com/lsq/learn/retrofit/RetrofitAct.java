package com.lsq.learn.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lsq.learn.R;
import com.lsq.learn.retrofit.api.Api;
import com.lsq.learn.retrofit.modle.Repo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAct extends AppCompatActivity {
    private static final String TAG = "RetrofitAct";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .build();

        Api api=retrofit.create(Api.class);

        Call<List<Repo>> getResponCall=api.getGithubUserRepo("rengwuxian");

        getResponCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                for (Repo repo:response.body()) {
                    Log.e(TAG, "onResponse: " +repo.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }
}
