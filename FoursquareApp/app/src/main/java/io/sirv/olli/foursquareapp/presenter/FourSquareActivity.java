package io.sirv.olli.foursquareapp.presenter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import android.widget.ListView;

import java.util.List;


import io.sirv.olli.foursquareapp.Base.BaseActivity;
import io.sirv.olli.foursquareapp.R;
import io.sirv.olli.foursquareapp.presenter.Services.CustomAdapter;
import io.sirv.olli.foursquareapp.presenter.model.LocationHelpers;
import io.sirv.olli.foursquareapp.presenter.model.Venue;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public class FourSquareActivity extends BaseActivity<FourSquarePresenter> implements FourSquarecontract.View {

    private CustomAdapter customAdapter;

    private ListView listView;

    public EditText searchText;

    LocationHelpers locationHelpers = new LocationHelpers();

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    public static boolean hasPermissions(Context context, String... allPermissionNeeded)
    {

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && context != null && allPermissionNeeded != null)
            for (String permission : allPermissionNeeded)
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
        return true;

    }


    @Override
    protected void init(Bundle savedInstanceState) {
        if(hasPermissions(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
            /*
            Does not perform any functions unless location permission has been granted.
             */


            locationHelpers.LocationActivity = this; // Anti-Pattern solution to pass context and activity to presenter. Could not find a better solution.

            locationHelpers.LocationContext = this; // Anti-Pattern solution to pass context and activity to presenter. Could not find a better solution.

            listView = findViewById(R.id.listView);

            searchText = findViewById(R.id.searchField);

            searchText.addTextChangedListener(new TextWatcher() {

                @Override
                public void afterTextChanged(Editable s) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {

                    getPresenter().onTextChanged(s, locationHelpers);
                }
            });


        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            init(savedInstanceState);}
    }

    @Override
    public void injectDependencies() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onListViewPopulate(List<Venue> venues){

        /*

        List view uses custom apadter. Services/CustomAdapter

         */

        customAdapter = new CustomAdapter(this,venues);


        listView.setAdapter(customAdapter);

    }




}

