package io.sirv.olli.foursquareapp.Base;

import io.sirv.olli.foursquareapp.presenter.FourSquarePresenter;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public class BasePresenter<V extends BaseView> implements BaseMvpPresenter<V> {

    private V mView;

    @Override
    public void attach(V view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public boolean isAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }
}
