package io.sirv.olli.foursquareapp.presenter.Services;

import io.sirv.olli.foursquareapp.presenter.model.RetrofitResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Olli-Pekka on 14/11/2017.
 */

public class ApiEndPoint{

        /*

     Creates necessary retrofit parts. Builds the query using static url. no other Query values assigned here.

     */


    public String URL = "https://api.foursquare.com/v2/";

        public interface FQApi

        {

            @GET("venues/search")
                Call<RetrofitResponse> getResults (
                @Query("client_id") String clientId,
                @Query("client_secret") String clientSecret,
                @Query("ll") String currentLocation,
                @Query("v") String v,
                @Query("query") String queryString);
        }

        public FQApi getAPI(){
                        Retrofit retrofit = new Retrofit
                                .Builder()
                                .baseUrl(URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        return retrofit.create(FQApi.class);
         }



}
