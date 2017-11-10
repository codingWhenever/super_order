package com.e_at.super_order.http.interceptor;


import com.e_at.super_order.http.download.FileLoadEvent;
import com.e_at.super_order.http.download.RxBus;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    private ResponseBody mResponseBody;
    private BufferedSource mBufferedSource;

    public ProgressResponseBody(ResponseBody responseBody) {
        this.mResponseBody = responseBody;
    }

    @Override
    public MediaType contentType() {
        return mResponseBody.contentType();
    }

    @Override
    public long contentLength() {
        return mResponseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (mBufferedSource == null) {
            mBufferedSource = Okio.buffer(source(mResponseBody.source()));
        }
        return mBufferedSource;
    }

    private Source source(BufferedSource source) {
        return new ForwardingSource(source) {
            long byteRead = 0;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                byteRead += bytesRead == -1 ? 0 : bytesRead;
                //实时发送当前已读取的字节和总字节
                RxBus.getInstance().post(new FileLoadEvent(contentLength(), bytesRead));
                return bytesRead;
            }
        };
    }
}
