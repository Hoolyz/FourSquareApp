package io.sirv.olli.foursquareapp.presenter.model;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Olli-Pekka on 14/11/2017.
 */



public class ApiEndPoint{



        private static String URL = "https://api.foursquare.com/v2/";

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




        // String apiQuery=" https://api.foursquare.com/v2/venues/search?v=20171411&ll=60.1705171%2C24.935404&query=Helsinki&intent=checkin&radius=30000&client_id=CYEMKOM4OLTP5PHMOFVUJJAMWT5CH5G1JBCYREATW21XLLSZ&client_secret=GYNP4URASNYRNRGXR5UEN2TGTKJHXY5FGSAXTIHXEUG1GYM2;

}
