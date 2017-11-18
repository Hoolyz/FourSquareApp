package io.sirv.olli.foursquareapp.component;

import javax.inject.Singleton;

import dagger.Component;
import io.sirv.olli.foursquareapp.module.ActivityModule;

import io.sirv.olli.foursquareapp.presenter.FourSquareActivity;
import io.sirv.olli.foursquareapp.presenter.FourSquarePresenter;
import io.sirv.olli.foursquareapp.presenter.FourSquarecontract;

/**
 * Created by Olli-Pekka on 18/11/2017.
 */

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(FourSquareActivity obj);




}
