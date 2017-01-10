package com.example.tyler.jpm;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tyler on 1/9/2017.
 */
public class RetrofitSingleton {
    public static final String BASE_URL = "https://m.chase.com/";
    private static Urls mInstance;
    public static Urls getInstance(){
        if (mInstance == null){
            //Logging interceptor here is useful for debugging but not needed
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            mInstance = retrofit.create(Urls.class);
        }
        return mInstance;
    }
}
