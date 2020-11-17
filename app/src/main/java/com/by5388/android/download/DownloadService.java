package com.by5388.android.download;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

/**
 * @author by5388  on 2020/11/17.
 */
public class DownloadService extends Service {
    public static final String TAG = DownloadService.class.getSimpleName();
    public static final String PARAM_DOWNLOAD_URL = "downloadPath";
    private Handler mHandler;
    private HandlerThread mBackgroundThread;
    private Handler mBackgroundHandler;

    public static Intent newIntent(Context context, String url) {
        final Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(PARAM_DOWNLOAD_URL, url);
        return intent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(Looper.getMainLooper());
        mBackgroundThread = new HandlerThread(TAG);
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mBackgroundThread.quitSafely();
        super.onDestroy();
    }
}
