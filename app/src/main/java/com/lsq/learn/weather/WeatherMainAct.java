package com.lsq.learn.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.lsq.learn.BaseActivity;
import com.lsq.learn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

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
        new Retrofit.Builder()
                .baseUrl("http://jisutqybmf.market.alicloudapi.com/")
                .addConverterFactory()
                .build()
                .create(api.class)
                .getWeather("北京")
        ;
    }

    @OnClick(R.id.bt_weather)
    public void onViewClicked() {

    }
}
