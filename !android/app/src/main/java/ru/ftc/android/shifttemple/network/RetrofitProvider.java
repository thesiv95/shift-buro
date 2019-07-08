package ru.ftc.android.shifttemple.network;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:32
 */

public final class RetrofitProvider {

    private static final String BASE_URL = "http://10.9.49.202:8081/";
    private static final int CONNECT_TIMEOUT_SECONDS = 2;

    private final Retrofit retrofit;

    public RetrofitProvider(List<Interceptor> interceptorList) {        
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(createClient(interceptorList))
                .build();
    }

    private OkHttpClient createClient(List<Interceptor> interceptorList) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        for (Interceptor interceptor: interceptorList) {
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

}