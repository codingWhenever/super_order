package com.e_at.super_order.http.interceptor;


import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Buffer requestBuffer = new Buffer();
        Request request = original.newBuilder().build();
        if (request.body() != null) {
            request.body().writeTo(requestBuffer);
        } else {
            Logger.e("request.body() == null !!!");
        }

        Logger.d(request.url() + (request.body() != null ? "?" + parseParams(request.body(), requestBuffer) : ""));

        return chain.proceed(request);
    }

    private String parseParams(RequestBody body, Buffer requestBuffer) throws UnsupportedEncodingException {
        if (body.contentType() != null && !body.contentType().toString().contains("multipart")) {
            return URLDecoder.decode(requestBuffer.readUtf8(), "UTF-8");
        }
        return "null";
    }
}
