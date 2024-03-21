package com.d4smart.fastonshelf.bean;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class HttpCmpConfiguration {

    @Bean
    @Qualifier("okHttpClient")
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }
}
