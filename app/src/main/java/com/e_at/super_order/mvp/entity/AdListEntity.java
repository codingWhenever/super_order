package com.e_at.super_order.mvp.entity;


import java.util.List;

public class AdListEntity extends BaseEntity {

    private List<AdEntity> response;

    public List<AdEntity> getResponse() {
        return response;
    }

    public static class AdEntity {
        public int adId;
        public int adDuration;
        public String adImgPath;
    }
}
