package com.lsq.learn.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

import java.util.List;
import java.util.concurrent.TimeUnit;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by lsq on 2018/3/18.
 */

public class WeatherMainAct extends BaseActivity {
    @BindView(R.id.tv_weather)
    TextView tvWeather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_weather_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.bt_weather)
    public void onViewClicked() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);

        new Retrofit.Builder()
                .baseUrl("http://jisutqybmf.market.alicloudapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
                .create(api.class)
                .getWeather("北京")
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<weatherBean, ObservableSource<weatherBean.ResultBean.HourlyBean>>() {
                    @Override
                    public ObservableSource<weatherBean.ResultBean.HourlyBean> apply(weatherBean weatherBean) throws Exception {
                        List<weatherBean.ResultBean.HourlyBean> hourlyBeanList = weatherBean.getResult().getHourly();
                        return Observable.fromIterable(hourlyBeanList);
                    }
                })
                .filter(new Predicate<weatherBean.ResultBean.HourlyBean>() {
                    @Override
                    public boolean test(weatherBean.ResultBean.HourlyBean hourlyBean) throws Exception {
                        return true;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<weatherBean.ResultBean.HourlyBean>() {
                    @Override
                    public void accept(weatherBean.ResultBean.HourlyBean hourlyBean) throws Exception {
                        Log.e("hhh", "accept: " +hourlyBean.getTime());
                        Log.e("hhh", "accept: " +hourlyBean.getWeather());
                        tvWeather.setText(tvWeather.getText()+"\n"+hourlyBean.getTime()+hourlyBean.getWeather());
                    }
                });
    }
}
