package com.uetechnologies.ukemeobong.javadevinlagos;

/**
 * Created by Ukemeobong on 8/20/2017.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//This method initializes the Retrofit, calls the base url, converts JSON to app format and then builds
public class RestApiBuilder {

    public static final String BASE_URL = "https://api.github.com";

    private Retrofit retrofit;

    public RestApiBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //This method points to the service class
    public RestApiService getService() {
        return retrofit.create(RestApiService.class);
    }
}
