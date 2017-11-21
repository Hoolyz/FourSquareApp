package io.sirv.olli.foursquareapp.presenter;


import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

import io.sirv.olli.foursquareapp.Base.BasePresenter;
import io.sirv.olli.foursquareapp.presenter.Services.ApiEndPoint;
import io.sirv.olli.foursquareapp.presenter.model.ApiKeys;
import io.sirv.olli.foursquareapp.presenter.Services.ApiDateVersion;
import io.sirv.olli.foursquareapp.presenter.model.LocationHelpers;
import io.sirv.olli.foursquareapp.presenter.Services.LocationService;
import io.sirv.olli.foursquareapp.presenter.model.RetrofitResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public class FourSquarePresenter extends BasePresenter<FourSquarecontract.View> implements FourSquarecontract.Presenter{

    private Context mContext;

    String currentLocation = "";

    ApiEndPoint apiEndPoint = new ApiEndPoint();

    ApiKeys apiKeys = new ApiKeys();

    ApiDateVersion apiDateVersion = new ApiDateVersion();




    @Inject
    public FourSquarePresenter() {
    }


    public void onTextChanged(CharSequence query,LocationHelpers locationHelpers){


        LocationService locationService = new LocationService();

        currentLocation = locationService.getLocationService(locationHelpers.LocationActivity,locationHelpers.LocationContext);

        String currentDateVersion = apiDateVersion.currentDate;


            getFourSquare(query.toString(),currentLocation,currentDateVersion);


        }


    public void getFourSquare(String query,String currLoc,String version){


            apiEndPoint.getAPI().getResults(apiKeys.clientId, apiKeys.clientSecret, currLoc, version ,query).enqueue(new Callback<RetrofitResponse>() {

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



