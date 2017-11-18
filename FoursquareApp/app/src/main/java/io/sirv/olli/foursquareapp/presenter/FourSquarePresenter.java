package io.sirv.olli.foursquareapp.presenter;


import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import io.sirv.olli.foursquareapp.Base.BasePresenter;
import io.sirv.olli.foursquareapp.presenter.model.ApiEndPoint;
import io.sirv.olli.foursquareapp.presenter.model.ApiKeys;
import io.sirv.olli.foursquareapp.presenter.model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public class FourSquarePresenter extends BasePresenter<FourSquarecontract.View> implements FourSquarecontract.Presenter{

    private Context mContext;

    String findCurrentLocation = "";

    ApiEndPoint apiEndPoint = new ApiEndPoint();

    ApiKeys apiKeys = new ApiKeys();

    @Inject
    public FourSquarePresenter() {
    }

    public void foo(Context context) {
        // when you need location
        // if inside activity context = this;

        SingleShotLocationProvider.requestSingleUpdate(context,
                new SingleShotLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                        Log.i("Location", "my location is " + location.toString());
                    }
                });
    }


    public void onTextChanged(CharSequence query){


        foo(mContext);

            findCurrentLocation = "60.1705171,24.935404";

            Log.i("Gps",findCurrentLocation.toString());

            getFourSquare(query.toString(),findCurrentLocation.toString());


        }


    public void getFourSquare(String query,String currLoc){


            apiEndPoint.getAPI().getResults(apiKeys.clientId, apiKeys.clientSecret, currLoc, "20171411" ,query).enqueue(new Callback<RetrofitResponse>() {

                @Override
                public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response)
                {
                    RetrofitResponse result = response.body();

                    if(result != null)
                        getView().onListViewPopulate(result.getResponse().getVenues());
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


        }


}



