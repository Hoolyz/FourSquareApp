package io.sirv.olli.foursquareapp.Base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import javax.inject.Inject;

import dagger.Provides;
import io.sirv.olli.foursquareapp.component.ActivityComponent;
import io.sirv.olli.foursquareapp.component.DaggerActivityComponent;
import io.sirv.olli.foursquareapp.module.ActivityModule;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public abstract class BaseActivity<T extends BaseMvpPresenter> extends AppCompatActivity implements BaseView {


    @Inject
    T mPresenter;



    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());


        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
        injectDependencies();
        mPresenter.attach(this);
        init(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detach();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    public T getPresenter() {
        return mPresenter;
    }


    @LayoutRes
    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();
}
