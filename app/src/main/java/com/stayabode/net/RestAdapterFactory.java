package com.stayabode.net;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.stayabode.BuildConfig;
import com.stayabode.app.AbodeApplication;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import timber.log.Timber;
import utils.SharedPrefManager;

/**
 * Created by VarunBhalla on 13/10/16.
 */

public class RestAdapterFactory {

    public RestAdapterFactory() {

    }

    public interface RestResponseListener {
        void getRestResponseCode(int code);
    }

    private static final String MOCK_API_ROOT = "http://www.mocky.io/";
    private static final String STAGING_API_ROOT = "http://staging.stayabode.com/";
    private static final String LOCAL_API_ROOT = "http://68.183.81.118:8080/icds/";


    RestResponseListener mRestResponseListener;

    private RestApi mRestApi;

    private static Retrofit sRetrofit;
    static Cache sCache;
    private static long SIZE_OF_CACHE = 48 * 1024 * 1024; // 48 MB

    static {
        sCache = new Cache(new File(AbodeApplication.getContext().getCacheDir(), "http"), SIZE_OF_CACHE);
    }

    public enum RequestType {
        AUTH, NONE, MOCK, STAGING,LOCAL
    }

    public void setRestResponseListener(RestResponseListener listener) {
        mRestResponseListener = listener;
    }

    Interceptor ResponseCodeInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            if (mRestResponseListener != null) {
                mRestResponseListener.getRestResponseCode(response.code());
            }
            return chain.proceed(request);
        }
    };

    public static RestApi newInstance(RequestType requestType) {
        OkHttpClient client = new OkHttpClient();
       // client.setConnectTimeout(15, TimeUnit.SECONDS);
        client.setReadTimeout(30, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(getLogLevel());


        Interceptor authInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Timber.v("Request url is : %s and the request headers are %s , header is %s", original.url(), original.headers(), original.body());

                String accessToken = SharedPrefManager.getInstance().getAccessToken();
                String accessTokenKey = SharedPrefManager.getInstance().getAccessTokenKey();

                Request.Builder requestBuilder = original.newBuilder()
                        .header(accessTokenKey, accessToken)
                        .header("devicetype", "android")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                Response response = null;
                boolean responseOK = false;

                int tryCount = 0;
                while (!responseOK && tryCount < 2) {
                    tryCount++;
                    response = chain.proceed(request);
                    responseOK=response.isSuccessful();
                }

                return response;
            }
        };

        switch (requestType) {
            case AUTH:
                client.interceptors().add(authInterceptor);
                break;

            case NONE:
                break;

            case MOCK:
                break;

            case STAGING:
                client.interceptors().add(authInterceptor);
                break;

            case LOCAL:
                client.interceptors().add(authInterceptor);
                break;
        }

        client.interceptors().add(loggingInterceptor);


        sRetrofit = new Retrofit.Builder()
                .baseUrl(LOCAL_API_ROOT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .callbackExecutor(new MainThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return sRetrofit.create(RestApi.class);
    }

    public static void removeFromCache(String urlString) {
        try {
            Iterator<String> it = sCache.urls();

            while (it.hasNext()) {
                String next = it.next();

                if (next.contains(urlString)) {
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeFromCache(String... urls) {
        if (urls == null) {
            return;
        }

        for (String url : urls) {
            removeFromCache(url);
        }
    }

    private static HttpLoggingInterceptor.Level getLogLevel() {
        return HttpLoggingInterceptor.Level.BODY;
    }
}
