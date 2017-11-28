package io.sirv.olli.foursquareapp.presenter;

import java.util.List;

import io.sirv.olli.foursquareapp.Base.BaseMvpPresenter;
import io.sirv.olli.foursquareapp.Base.BaseView;
import io.sirv.olli.foursquareapp.presenter.model.LocationHelpers;
import io.sirv.olli.foursquareapp.presenter.model.Venue;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

/*

Passes key press events to presenter and returns data from presenter.

 */

public interface FourSquarecontract  {

    interface Presenter extends BaseMvpPresenter<View> {
        void onTextChanged(CharSequence query,LocationHelpers locationHelpers);

    }
    
    interface View extends BaseView {
        void onListViewPopulate(List<Venue> venue);
    }


}
