package com.e_at.super_order.http;

/**
 * Created by dev07 on 2016/12/20.
 */

public class Api {
    // test        wx9e665aac0080302f      64020361b8ec4c99936c0e3999a9f249
    // release     wx7428c108cb8dc612      f02987152e031d88055a10fe202d3476
    public static String WX_APPID = "wx9e665aac0080302f";//此appid可动态匹配不同品牌
    public static final String WX_SECRET = "64020361b8ec4c99936c0e3999a9f249";
//    public static final String BASE_URL = "http://192.168.10.118:8080/";
//    public static final String BASE_URL = "http://dsyc.oicp.net:8081/";
//    public static final String BASE_URL = "https://192.168.10.10/";


    public static int DURATION = 3;//tipview显示持续3秒钟
    //apiapp.e-at.cc
    //apiapp.yi-ai.cc
    public static String BASE_URL = "https://apiapp.yi-ai.cc/";
//    public static final String BASE_URL = "https://apiapp.e-at.cc/";
//    public static final String BASE_URL = "http://192.168.10.190:8080/";

    //抽奖活动链接前缀
    public static final String LOTTERY_URL_PREFIX_DEBUG = "https://pro.yi-ai.cc/wheel/lottery.do?jsonParams=";
    public static final String LOTTERY_URL_PREFIX_RELEASE = "https://pro.e-at.cc/wheel/lottery.do?jsonParams=";

    //转介绍活动未登录页面连接
    public static final String URL_SHARE_PROMOTION_TEST = "https://wx.yi-ai.cc/apphtml/no_login_index.jsp";
    public static final String URL_SHARE_PROMOTION_RELEASE = "https://wx.e-at.cc/apphtml/no_login_index.jsp";
    //满赠活动页面前缀
    public static final String URL_PROMOTION_GIFT_PREFIX_TEST = "https://pro.yi-ai.cc/fuugive/index.do?jsonParams=";
    public static final String URL_PROMOTION_GIFT_PREFIX_RELEASE = "https://pro.e-at.cc/fuugive/index.do?jsonParams=";


    public static final String openFood = "phone/open/foods";
    public static final String openStores = "phone/open/getAllStores";

    public static final String getAllStores = "phone/getAllStores";
    public static final String scanStore =  "phone/scanStore";
    public static final String foods = "phone/foods";
    public static final String generalfoodinfo = "phone/generalfoodinfo";//普通餐品
    public static final String fixationfoodinfo = "phone/fixationfoodinfo";//固定套餐
    public static final String packagefoodinfo = "phone/packagefoodinfo";//可换套餐
    public static final String orderList = "api/phone/order/orderList";//订单列表
    public static final String orderDetail = "api/phone/order/orderDetail";//订单详情
    public static final String deleteOrder = "api/phone/order/deleteOrder";//删除订单
    public static final String findBoxById = "phone/findBoxById";//获取打包盒信息
    public static final String readyPay = "phone/readyPay";//预备支付信息
    public static final String generateOrder = "api/phone/order/generateOrder";//生成订单
    public static final String goPay = "api/phone/order/goPay";//去支付
    public static final String getShareLogInfo = "wechat/getShareLogInfo";//获取转介绍活动信息
    public static final String addShareLog = "wechat/addShareLog";//保存分享记录
    public static final String addGroupMember = "wechat/addGroupMember";//品牌会员注册接口

    public static final String findAllAddress = "phone/findAllAddress";//获取会员个人外卖地址
    public static final String getResAddress = "phone/getResAddress";//获取门店外卖地址
    public static final String saveAddress = "phone/saveAddress";//新增外卖地址
    public static final String delAddress = "phone/delAddress";//删除外卖地址
    public static final String updAddress = "phone/updAddress";//修改外卖地址


    public static final String checkGroupPromotion = "phone/open/checkGroupPromotion";//检查品牌活动是否存在
    public static final String login = "api/phone/member/login";//登录
    public static final String getGroupInfo = "/phone/getGroupInfo";         //品牌列表
    public static final String groupMemberAmountBar = "/api/phone/member/groupMemberAmountBar";     //会员余额列表
    public static final String updateMember = "/api/phone/member/updateMember";      //设置会员信息
    public static final String getSMSCode = "api/phone/member/getSMSCode";          //获取短信验证码
    public static final String getVoiceCode = "api/phone/member/getVoiceCode";//获取语音验证码
    public static final String groupMemberRecharge = "/api/phone/member/groupMemberRecharge";            //会员充值记录
    public static final String groupMemberCoupon = "/api/phone/member/groupMemberCoupon";        //会员优惠券
    public static final String groupMemberCouponDetails = "/api/phone/member/groupMemberCouponDetails";    //优惠券详情
    public static final String groupCardIdList = "/api/phone/member/groupCardIdList";    //会员卡列表
    public static final String groupCardInfo = "/api/phone/member/groupCardInfo";    //购卡记录
    public static final String groupMemberBal = "/api/phone/member/groupMemberBal";    //品牌会员余额信息
    public static final String getMemberCardList = "/api/phone/member/getMemberCardList";    //品牌会员卡列表
    public static final String pmMemberInfo = "/api/phone/member/pmMemberInfo";    //平台会员信息
    public static final String updatePMMemberPhone = "/api/phone/member/updatePMMemberPhone";    //修改平台会员手机号
    public static final String exit = "/api/phone/member/exit";    //退出
    public static final String memberRecharge = "/api/phone/member/memberRecharge";    //充值
    public static final String getPayList = "/api/phone/member/getPayList";          //获取支付方式列表
    public static final String updateApp = "/uploadApp/updateApp";       //版本更新
    public static final String getGroupMemberEntityCard = "/api/phone/member/getGroupMemberEntityCard";       //根据平台会员Id获取品牌会员实体卡
    public static final String lossEntityCard = "/api/phone/member/lossEntityCard";       //挂失
    public static final String getGroupMemberDetail = "/api/phone/member/getGroupMemberDetail";       //获取会员详情

    public static final String getOrderComment = "api/phone/order/getOrderComment";//获取评价
    public static final String addOrderComment = "api/phone/order/addOrderComment";//订单评价

    public static final String hotActivity = "https://api.e-at.cc/agreement/hotActivities.html";//热门活动
    public static final String commonProblem = "https://api.e-at.cc/agreement/commonProblem.html";//常见问题

    public static final String getRechargeUI = "api/phone/member/getRechargeUI";//充值
    public static final String getAdList = "api/phone/member/getAdList";//获取广告

    public static final String getBannerList = "phone/open/getBannerList";//首页广告
    public static final String getMemberInfo = "phone/getMemberInfo";//获取品牌会员信息
    public static final String getOrderCallNo = "api/phone/order/getOrderCallNo";//获取未完成订单的就餐号


    public static final String addAddressPage = "phone/open/addAddressPage";//获取用户的收货地址和附近地址
    public static final String searchAddress = "phone/open/searchAddress";//查询地址
    public static final String getCityData = "phone/open/getAllCityForDataBase";//获取城市列表

    public static final String addNewAddress = "phone/addNewAddress";//新增平台收货地址
    public static final String updNewAddress = "phone/updNewAddress";//修改平台收货地址
    public static final String getNewAddressAll = "phone/getNewAddressAll";//获取平台用户收货地址

    /*****************************V2.1.15**************************************/
    public static final String giftInfo = "activity/giftInfo";//获取满赠活动信息
    public static final String specialInfo = "activity/specialInfo";//获取特价活动信息
    public static final String generateOrderNotPayType = "api/phone/order/generateOrderNotPayType";//不带支付方式生成订单
    public static final String goOrderPay = "api/phone/order/goOrderPay";//订单支付接口
    public static final String getDispatchInfo = "api/phone/order/getDispatchInfo";//下单外卖配送时间


}
