package io.sirv.olli.foursquareapp.Base;

import android.Manifest;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

import javax.inject.Inject;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());

        checkForUpdates();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        /*

        Dagger 2 builder.
         */

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


        @Override
        public void onResume() {
            super.onResume();
            // ... your own onResume implementation
            checkForCrashes();
        }

        @Override
        public void onPause() {
            super.onPause();
            unregisterManagers();
        }


        private void checkForCrashes() {
            CrashManager.register(this);
        }

        private void checkForUpdates() {
            // Remove this for store builds!
            UpdateManager.register(this);
        }

        private void unregisterManagers() {
            UpdateManager.unregister();
        }

    }
