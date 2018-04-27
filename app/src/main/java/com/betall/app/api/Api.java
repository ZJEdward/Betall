package com.betall.app.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by fly on 2018/1/26.
 */

public final class Api {
    /**
     * Don't let anyone instantiate this class.
     */
    private Api() {

    }

    public static ApiService getDefault() {
        return service;
    }

    private static ApiService service = createService();

    // 常量
    private static final String BASE_URL = "http://ip.taobao.com/service/";
    private static final int TIMEOUT = 60;

    private static ApiService createService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ApiInterceptor())
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build();

        return retrofit.create(ApiService.class);
    }
}
