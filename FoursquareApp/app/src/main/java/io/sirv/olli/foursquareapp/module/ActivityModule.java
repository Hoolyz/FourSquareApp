package io.sirv.olli.foursquareapp.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import io.sirv.olli.foursquareapp.presenter.FourSquarePresenter;
import io.sirv.olli.foursquareapp.presenter.FourSquarecontract;

/**
 * Created by Olli-Pekka on 18/11/2017.
 */

@Module
public class ActivityModule {

    private Context mContext;


    public ActivityModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    FourSquarecontract.Presenter providesMainPresenter() {
        return new FourSquarePresenter();
    }



}