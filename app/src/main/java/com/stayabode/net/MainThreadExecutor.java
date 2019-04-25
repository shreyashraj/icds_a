package com.stayabode.net;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

/**
 * Created by VarunBhalla on 13/10/16.
 */
public class MainThreadExecutor implements Executor {
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(Runnable r) {
        mHandler.post(r);
    }

    public void executeInBackground(Runnable r) {
        Thread thread = new Thread(r);
        thread.start();
    }

    @Override
    public String toString() {
        return "MainThreadExecutor";
    }
}