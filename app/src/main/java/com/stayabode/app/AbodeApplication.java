package com.stayabode.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.stayabode.BuildConfig;
import com.stayabode.R;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by VarunBhalla on 05/10/16.
 */
public class AbodeApplication extends Application {
    private static volatile Context sContext;
    private static MixpanelAPI mixpanel;

    public static Context getContext() {
        return sContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(true)
                .build();

     //   Fabric.with(fabric);

        String devToken = getResources().getString(R.string.mixpanel_dev_token); // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"
        String prodToken = getResources().getString(R.string.mixpanel_prod_token); // e.g.: "1ef7e30d2a58d27f4b90c42e31d6d7ad"

        if (BuildConfig.DEBUG) {
            mixpanel = MixpanelAPI.getInstance(this, devToken);
        } else {
            mixpanel = MixpanelAPI.getInstance(this, prodToken);
        }


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        {
            Timber.plant(new Timber.DebugTree() {
                private static final int MAX_LOG_LENGTH = 4000;

                // Shows the method name and line number along with the log
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getMethodName() + ":" + element.getLineNumber();
                }

                /**
                 * Break up {@code message} into maximum-length chunks (if needed) and send to either
                 * {@link Log#println(int, String, String) Log.println()} or
                 * {@link Log#wtf(String, String) Log.wtf()} for logging.
                 * {@inheritDoc}
                 */
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    if (message.length() < MAX_LOG_LENGTH) {
                        if (priority == Log.ASSERT) {
                            Log.wtf(tag, message);
                        } else {
                            Log.println(priority, tag, message);
                        }
                        return;
                    }

                    // Split by line, then ensure each line can fit into Log's maximum length.
                    for (int i = 0, length = message.length(); i < length; i++) {
                        int newline = message.indexOf('\n', i);
                        newline = newline != -1 ? newline : length;
                        do {
                            int end = Math.min(newline, i + MAX_LOG_LENGTH);
                            String part = message.substring(i, end);
                            if (priority == Log.ASSERT) {
                                Log.wtf(tag, part);
                            } else {
                                Log.println(priority, tag, part);
                            }
                            i = end;
                        } while (i < newline);
                    }
                }
            });
        }
    }


    public static MixpanelAPI getMixpanel() {
        return mixpanel;
    }

}
