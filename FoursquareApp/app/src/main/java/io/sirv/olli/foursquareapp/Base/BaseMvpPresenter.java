package io.sirv.olli.foursquareapp.Base;


public interface BaseMvpPresenter<V extends BaseView> {


    void attach(V view);


    void detach();


    boolean isAttached();
}