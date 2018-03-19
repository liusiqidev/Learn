package com.lsq.learn.weather;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by lsq on 18-3-19.
 */

public interface api {
    @FormUrlEncoded
    @GET("/weather/query")
    Observable<weatherBean> getWeather(@Field("city") String city);
}
