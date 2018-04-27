package com.betall.app.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fly on 2018/1/26.
 */

public class IpInfo {
    public String country;

    @SerializedName("country_id")
    public String countryId;
    public String area;
    @SerializedName("area_id")
    public String areaId;
    public String region;
    @SerializedName("region_id")
    public String regionId;
    public String city;
    @SerializedName("city_id")
    public String cityId;
    public String county;
    @SerializedName("county_id")
    public String countyId;
    public String isp;
    @SerializedName("isp_id")
    public String ispId;
    public String ip;
}
