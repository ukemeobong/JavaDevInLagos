package com.uetechnologies.ukemeobong.javadevinlagos;

/**
 * Created by Ukemeobong on 8/20/2017.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

//This method locates the URL and fetches the data which is list of
// Github users in the filter(location) and passes it to the users class

public interface RestApiService {

    @GET("/search/users")
    Call<UserList> getUserList(@Query("q") String filter);

}

