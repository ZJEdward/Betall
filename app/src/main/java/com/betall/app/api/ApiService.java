package com.betall.app.api;


import com.betall.app.bean.IpInfo;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by fly on 2018/1/26.
 */

public interface ApiService {
    @GET("getIpInfo.php")
    public Single<IpInfo> getIpInfo(@Query("ip") String ip);


    @FormUrlEncoded
    @POST("getIpInfo.php")
    public Single<IpInfo> postInInfo(@Field("ip") String ip);
}
