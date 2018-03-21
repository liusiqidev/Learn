package com.lsq.learn.weather;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Query;


/**
 * Created by lsq on 18-3-19.
 */

public interface api {
    @Headers("Authorization:APPCODE 847b64712afb4b4cab0e6deb0b0af04f")
    @GET("weather/query")
    Observable<weatherBean> getWeather(@Query("city") String city);
}
