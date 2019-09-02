package com.amap.api.maps.model;

public class PoiPara {
    private LatLng center;
    private String keywords;

    public LatLng getCenter() {
        return this.center;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setCenter(LatLng latLng) {
        this.center = latLng;
    }

    public void setKeywords(String str) {
        this.keywords = str;
    }
}
