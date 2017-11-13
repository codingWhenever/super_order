package com.e_at.super_order.mvp.entity;

/**
 * Created by dev07 on 2017/3/8.
 */

public class PromotionEntity extends BaseEntity {

    private Promotion response;

    public Promotion getResponse() {
        return response;
    }

    public static class Promotion {
        private int isPromotion;//是否有活动
        private int promotionId;
        private int memberId;
        private String defaultUrl;
        private String defaultTitle;
        private String defaultDesc;
        private String shareUrl;
        private int userType;

        public int getIsPromotion() {
            return isPromotion;
        }

        public int getPromotionId() {
            return promotionId;
        }

        public int getUserType() {
            return userType;
        }

        public String getShareUrl() {
            return shareUrl;
        }

        public String getDefaultDesc() {
            return defaultDesc;
        }

        public String getDefaultTitle() {
            return defaultTitle;
        }

        public String getDefaultUrl() {
            return defaultUrl;
        }

        public int getMemberId() {
            return memberId;
        }
    }
}
