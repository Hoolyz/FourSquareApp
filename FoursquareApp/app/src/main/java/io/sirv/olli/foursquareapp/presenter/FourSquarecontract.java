package io.sirv.olli.foursquareapp.presenter;

import java.util.List;

import io.sirv.olli.foursquareapp.Base.BaseMvpPresenter;
import io.sirv.olli.foursquareapp.Base.BaseView;
import io.sirv.olli.foursquareapp.Base.BasePresenter;
import io.sirv.olli.foursquareapp.presenter.model.RetrofitResponse;
import io.sirv.olli.foursquareapp.presenter.model.Venue;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public interface FourSquarecontract  {

    interface Presenter extends BaseMvpPresenter<View> {
        void onTextChanged(CharSequence query);

    }
    
    interface View extends BaseView {
        void onListViewPopulate(List<Venue> venue);
    }


}
