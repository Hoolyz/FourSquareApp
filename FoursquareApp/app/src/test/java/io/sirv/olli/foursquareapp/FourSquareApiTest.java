package io.sirv.olli.foursquareapp;

import android.util.Log;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import io.sirv.olli.foursquareapp.presenter.Services.ApiEndPoint;
import io.sirv.olli.foursquareapp.presenter.model.ApiKeys;
import io.sirv.olli.foursquareapp.presenter.model.RetrofitResponse;
import okhttp3.Callback;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Olli-Pekka on 22/11/2017.
 */

public class FourSquareApiTest {

    private MockWebServer server;

    public String testiString = "{\n" +
            "  \"meta\": {\n" +
            "    \"code\": 200,\n" +
            "    \"requestId\": \"5a15b0406a60714ef39cc583\"\n" +
            "  },\n" +
            "  \"response\": {\n" +
            "    \"venues\": [\n" +
            "      {\n" +
            "        \"id\": \"4b41efedf964a5207cca25e3\",\n" +
            "        \"name\": \"Nordea Vallila Campus\",\n" +
            "        \"contact\": {\n" +
            "          \"phone\": \"+3582003000\",\n" +
            "          \"formattedPhone\": \"+358 200 3000\",\n" +
            "          \"twitter\": \"Nordea_FI\"\n" +
            "        },\n" +
            "        \"location\": {\n" +
            "          \"address\": \"Aleksis Kiven katu 7\",\n" +
            "          \"lat\": 60.18992388921314,\n" +
            "          \"lng\": 24.954993944660167,\n" +
            "          \"labeledLatLngs\": [\n" +
            "            {\n" +
            "              \"label\": \"display\",\n" +
            "              \"lat\": 60.18992388921314,\n" +
            "              \"lng\": 24.954993944660167\n" +
            "            }\n" +
            "          ],\n" +
            "          \"distance\": 2417,\n" +
            "          \"postalCode\": \"00500\",\n" +
            "          \"cc\": \"FI\",\n" +
            "          \"city\": \"Helsinki\",\n" +
            "          \"state\": \"Uusimaa\",\n" +
            "          \"country\": \"Finland\",\n" +
            "          \"formattedAddress\": [\n" +
            "            \"Aleksis Kiven katu 7\",\n" +
            "            \"00500 Helsinki\",\n" +
            "            \"Finland\"\n" +
            "          ]\n" +
            "        },\n" +
            "        \"categories\": [\n" +
            "          {\n" +
            "            \"id\": \"4bf58dd8d48988d124941735\",\n" +
            "            \"name\": \"Office\",\n" +
            "            \"pluralName\": \"Offices\",\n" +
            "            \"shortName\": \"Office\",\n" +
            "            \"icon\": {\n" +
            "              \"prefix\": \"https://ss3.4sqi.net/img/categories_v2/building/default_\",\n" +
            "              \"suffix\": \".png\"\n" +
            "            },\n" +
            "            \"primary\": true\n" +
            "          }\n" +
            "        ],\n" +
            "        \"verified\": false,\n" +
            "        \"stats\": {\n" +
            "          \"checkinsCount\": 6140,\n" +
            "          \"usersCount\": 510,\n" +
            "          \"tipCount\": 1\n" +
            "        },\n" +
            "        \"url\": \"http://www.nordea.fi\",\n" +
            "        \"venueRatingBlacklisted\": true,\n" +
            "        \"allowMenuUrlEdit\": true,\n" +
            "        \"beenHere\": {\n" +
            "          \"lastCheckinExpiredAt\": 0\n" +
            "        },\n" +
            "        \"specials\": {\n" +
            "          \"count\": 0,\n" +
            "          \"items\": []\n" +
            "        },\n" +
            "        \"hereNow\": {\n" +
            "          \"count\": 0,\n" +
            "          \"summary\": \"Nobody here\",\n" +
            "          \"groups\": []\n" +
            "        },\n" +
            "        \"referralId\": \"v-1511370816\",\n" +
            "        \"venueChains\": [],\n" +
            "        \"hasPerk\": false\n" +
            "      }  \n" +
            "    ]\n" +
            "  }\n" +
            "}";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        server = new MockWebServer();
        server.start();
    }

    RetrofitResponse testiResponse;

    @Test
    public void test() throws IOException {

        Gson gson = new Gson();

         testiResponse = gson.fromJson(testiString,RetrofitResponse.class);


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(server.url(""))
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(testiString);

        server.enqueue(response);


        ApiEndPoint.FQApi service = retrofit.create(ApiEndPoint.FQApi.class);

        service.getResults("", "", "60.1705171,24.935404", "20172011", "testi").enqueue(new retrofit2.Callback<RetrofitResponse>() {

            @Override
          public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
            RetrofitResponse result = response.body();

            assertTrue(response != null);
              assertEquals(result, testiResponse);
              }

                @Override
                public void onFailure(Call<RetrofitResponse> call, Throwable t) {

                 try {
                     throw new InterruptedException(":(");

                   } catch (InterruptedException e) {
                       e.printStackTrace();
                       }

                       }
               });

        //Finish web server
        server.shutdown();







}
    }
