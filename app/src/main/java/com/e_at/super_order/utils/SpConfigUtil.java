package com.e_at.super_order.utils;

import android.content.Context;
import android.text.TextUtils;

import com.e_at.super_order.http.Api;

/**
 * SpConfigUtil
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class SpConfigUtil {
    /**
     * 设置deviceid
     *
     * @param context
     * @param deviceId
     */
    public static void setDeviceId(Context context, String deviceId) {
        SharedPreferencesUtil.setParam(context, "deviceId", deviceId);
    }

    /**
     * 获取设备id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "deviceId", "0");
    }

    /**
     * 设置token
     *
     * @param context
     * @param token
     */
    public static void setToken(Context context, String token) {
        SharedPreferencesUtil.setParam(context, "token", token);
    }

    /**
     * 获取token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "token", "");
    }

    /**
     * 是否开启会员双轨制
     *
     * @param context
     * @return
     */
    public static boolean isOpenVIP(Context context) {
        return (Boolean) SharedPreferencesUtil.getParam(context, "openVIP", false);
    }

    public static void setOpenVIP(Context context, boolean openVIP) {
        SharedPreferencesUtil.setParam(context, "openVIP", openVIP);
    }

    /**
     * 设置平台会员id
     *
     * @param context
     * @param platformMemberId
     */
    public static void setPlatformMemberId(Context context, int platformMemberId) {
        SharedPreferencesUtil.setParam(context, "platformMemberId", platformMemberId);
    }

    /**
     * 获取品牌会员id
     *
     * @param context
     * @return
     */
    public static int getMemberId(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "memberId", 0);
    }

    /**
     * 设置品牌会员id
     *
     * @param context
     * @param memberId
     */
    public static void setMemberId(Context context, int memberId) {
        SharedPreferencesUtil.setParam(context, "memberId", memberId);
    }

    /**
     * 获取平台会员id
     *
     * @param context
     * @return
     */
    public static int getPlatFormMemberId(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "platformMemberId", 0);
    }

    /**
     * 设置手机号
     *
     * @param context
     * @param phone
     */
    public static void setPhone(Context context, String phone) {
        SharedPreferencesUtil.setParam(context, "phone", phone);
    }

    /**
     * 获取手机号
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "phone", "");
    }

    /**
     * 设置昵称
     *
     * @param context
     * @param nickname
     */
    public static void setNickname(Context context, String nickname) {
        SharedPreferencesUtil.setParam(context, "nickname", nickname);
    }

    /**
     * 获取昵称
     *
     * @param context
     * @return
     */
    public static String getNickname(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "nickname", "");
    }

    /**
     * 设置品牌id
     *
     * @param context
     * @param resId
     */
    public static void setGroupID(Context context, String resId) {
        SharedPreferencesUtil.setParam(context, "groupId", resId);
    }

    /**
     * 获取商户ID
     *
     * @param context
     * @return
     */
    public static String getCompanyID(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "companyId", "");
    }

    /**
     * 设置商户id
     *
     * @param context
     * @param resId
     */
    public static void setCompanyID(Context context, String resId) {
        SharedPreferencesUtil.setParam(context, "companyId", resId);
    }


    /**
     * 获取品牌ID
     *
     * @param context
     * @return
     */
    public static String getGroupID(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "groupId", "");
    }


    /**
     * 设置门店ID
     *
     * @param context
     * @param resId
     */
    public static void setResID(Context context, String resId) {
        SharedPreferencesUtil.setParam(context, "resId", resId);
    }

    /**
     * 获取门店ID
     *
     * @param context
     * @return
     */
    public static String getResID(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "resId", "");
    }


    public static void setHasPromition(Context context, boolean hasPromotion) {
        SharedPreferencesUtil.setParam(context, "hasPromotion", hasPromotion);
    }

    /**
     * 当前是否有活动
     *
     * @param context
     * @return
     */
    public static boolean getHasPromotion(Context context) {
        return (boolean) SharedPreferencesUtil.getParam(context, "hasPromotion", false);
    }

    public static void setShareURLPrefix(Context context, String url) {
        SharedPreferencesUtil.setParam(context, "shareUrlPrefix", url);
    }

    public static void setPromotionId(Context context, int id) {
        SharedPreferencesUtil.setParam(context, "promotionId", id);
    }

    /**
     * 获取活动id
     *
     * @param context
     * @return
     */
    public static int getPromotionId(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "promotionId", 0);
    }

    /**
     * 获取分享链接前缀
     *
     * @param context
     * @return
     */
    public static String getShareURLPrefix(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "shareUrlPrefix", "");
    }

    public static void setDefaultShareURL(Context context, String url) {
        SharedPreferencesUtil.setParam(context, "defaultShareUrl", url);
    }

    /**
     * 获取默认转介绍地址
     *
     * @param context
     * @return
     */
    public static String getDefaultShareURL(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "defaultShareUrl", "");
    }


    public static void setDefaultShareTitle(Context context, String title) {
        SharedPreferencesUtil.setParam(context, "defaultShareTitle", title);
    }

    /**
     * 获取默认转介绍标题
     *
     * @param context
     * @return
     */
    public static String getDefaultShareTitle(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "defaultShareTitle", "");
    }


    public static void setDefaultShareDesc(Context context, String desc) {
        SharedPreferencesUtil.setParam(context, "defaultShareDesc", desc);
    }

    /**
     * 获取默认转介绍描述
     *
     * @param context
     * @return
     */
    public static String getDefaultShareDesc(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "defaultShareDesc", "");
    }

    /**
     * 设置当前抽奖活动
     *
     * @param context
     * @param lottery
     */
    public static void setLottery(Context context, int lottery) {
        SharedPreferencesUtil.setParam(context, "lottery", lottery == 1);
    }

    public static boolean getLottery(Context context) {
        return (boolean) SharedPreferencesUtil.getParam(context, "lottery", false);
    }

    /**
     * 保存门店名称（包含品牌+门店）
     *
     * @param context
     * @param resName
     */
    public static void setResName(Context context, String resName) {
        SharedPreferencesUtil.setParam(context, "resName", TextUtils.isEmpty(resName) ? "" : resName);
    }

    public static String getResName(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "resName", "");
    }

    /**
     * 保存品牌logo
     *
     * @param context
     * @param groupLogo
     */
    public static void setGroupLogo(Context context, String groupLogo) {
        SharedPreferencesUtil.setParam(context, "groupLogo", TextUtils.isEmpty(groupLogo) ? "" : groupLogo);
    }

    public static String getGroupLogo(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "groupLogo", "");
    }


    //起送金额
    public static void setDispatchMinMoney(Context context, String money) {
        SharedPreferencesUtil.setParam(context, "dispatch_min_money", money);
    }

    public static String getDispatchMinMoney(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "dispatch_min_money", "");
    }

    //满免金额
    public static void setDispatchFeeFree(Context context, String money) {
        SharedPreferencesUtil.setParam(context, "dispatch_fee_free", money);
    }

    public static String getDispatchFeeFree(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "dispatch_fee_free", "");
    }

    //配送金额
    public static void setDispatchFee(Context context, String money) {
        SharedPreferencesUtil.setParam(context, "dispatch_fee", money);
    }

    public static String getDispatchFee(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "dispatch_fee", "");
    }


    public static void setCurrentFragmentIndex(Context context, int index) {
        SharedPreferencesUtil.setParam(context, "fragmentIndex", index);
    }

    public static int getCurrentFragmentIndex(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "fragmentIndex", 0);
    }

    //保存后台域名
    public static void setBaseURL(Context context, String baseUrl) {
        SharedPreferencesUtil.setParam(context, "BaseURL", baseUrl);
    }

    public static String getBaseURL(Context context) {
        return (String) SharedPreferencesUtil.getParam(context, "BaseURL", Api.BASE_URL);
    }

    public static long getCreateOrderTime(Context context) {
        return (long) SharedPreferencesUtil.getParam(context, "createOrderTime", System.currentTimeMillis());
    }

    //设置当前待支付订单的临时生成订单时间
    public static void setCreateOrderTime(Context context, long time) {
        SharedPreferencesUtil.setParam(context, "createOrderTime", time);
    }

    /**
     * 保存当前的特价促销类型
     * 1=仅优惠最多的一件参与特价,2=特价餐品全部可以参与特价
     *
     * @return
     * @paramters
     */
    public static void setReductionType(Context context, int type) {
        SharedPreferencesUtil.setParam(context, "reductionType", type);
    }

    public static int getReductionType(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "reductionType", 0);
    }

    //特价活动类型
    public static void setSpecialActivityType(Context context, int type) {
        SharedPreferencesUtil.setParam(context, "specialType", type);
    }

    public static int getSpecialActivityType(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "specialType", 0);
    }

    //满赠活动类型
    public static void setGiftActivityType(Context context, int type) {
        SharedPreferencesUtil.setParam(context, "giftType", type);
    }

    public static int getGiftActivityType(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "giftType", 0);
    }

    //特价活动id
    public static void setSpecialActivityId(Context context, int id) {
        SharedPreferencesUtil.setParam(context, "specialId", id);
    }

    public static int getSpecialActivityId(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "specialId", 0);
    }

    //满赠活动id
    public static void setGiftActivityId(Context context, int id) {
        SharedPreferencesUtil.setParam(context, "giftId", id);
    }

    public static int getGiftActivityId(Context context) {
        return (int) SharedPreferencesUtil.getParam(context, "giftId", 0);
    }
}

