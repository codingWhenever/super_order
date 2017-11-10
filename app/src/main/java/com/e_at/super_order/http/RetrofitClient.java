package com.e_at.super_order.http;


import com.e_at.super_order.BuildConfig;
import com.e_at.super_order.http.interceptor.LoggingInterceptor;
import com.e_at.super_order.http.interceptor.ProgressInterceptor;
import com.e_at.super_order.http.interceptor.RequestHeaderInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitClient
 *
 * @author leo
 * @desc
 * @date 2017/11/9
 * @email lei.lu@e-at.com
 */
public class RetrofitClient {
    public static Retrofit sRetrofit;
    private static final int DEFAULT_TIME_OUT = 15;

    public static Retrofit initRetrofit() {
        if (sRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
                builder.addInterceptor(new LoggingInterceptor());
            }

            builder.addInterceptor(new ProgressInterceptor());
            //添加统一头部信息
            builder.addInterceptor(new RequestHeaderInterceptor());
            builder.retryOnConnectionFailure(false)//禁用失败重试
                    .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);//超时15秒

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(builder.build())
                    .build();
        }
        return sRetrofit;
    }

}
