package com.e_at.super_order.http.download;
/*===========================
 *author: jiangdingjun
 *create date: 2017/5/16
 *name:apk-file bean
 *update date:
 *===========================
 */

public class FileLoadEvent {

    long total;
    long bytesLoaded;

    public long getBytesLoaded() {
        return bytesLoaded;
    }

    public long getTotal() {
        return total;
    }

    public FileLoadEvent(long total, long bytesLoaded) {
        this.total = total;
        this.bytesLoaded = bytesLoaded;
    }
}
