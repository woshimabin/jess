package com.mobiletrain.teamapp.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by idea on 2016/10/8.
 */
public class HttpUtil {

    private static final String TAG ="test";
    private static File externalCacheDir;
    private static OkHttpClient okHttpClient;


    public static void okHttpAsyncGet(Context context, final String url, final Handler handler, final int msgWhat) {

        if (okHttpClient == null) {
            initOkHttpClient(context);
        }

        final Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String json = null;
                handlerSendMsg(json, handler, msgWhat);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "onResponse: " + Thread.currentThread().getName());

                if (response.isSuccessful()) {
                    String json = response.body().string();
                    handlerSendMsg(json, handler,msgWhat);
                } else {
                    String json = null;
                    handlerSendMsg(json, handler,msgWhat);
                }
            }
        });
    }

    private static void handlerSendMsg(String result, Handler handler,int msgWhat) {
        Message msg = handler.obtainMessage();
        msg.obj = result;
        msg.what = msgWhat;
        handler.sendMessage(msg);
    }

    private static void initOkHttpClient(Context context) {
        externalCacheDir = context.getExternalCacheDir();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .cache(new Cache(externalCacheDir, 20 * 1024 * 1024))
                .build();
    }
}
