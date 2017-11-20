package io.sirv.olli.foursquareapp;

import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import io.sirv.olli.foursquareapp.presenter.FourSquareActivity;
import io.sirv.olli.foursquareapp.presenter.FourSquarePresenter;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created by Olli-Pekka on 20/11/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ViewTests {
    private FourSquareActivity fourSquareActivity;
    @Mock
    FourSquarePresenter fourSquarePresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //fourSquareActivity = new FourSquareActivity(RuntimeEnvironment.application);
       // fourSquareActivity.attach(fourSquarePresenter);
    }

    @Test
    public void testEmpty() throws Exception {

        final String testedValue = null;
        verify(fourSquarePresenter).attach(fourSquareActivity);
        Mockito.doCallRealMethod().when(fourSquareActivity.searchText).setText(testedValue);
        Mockito.doCallRealMethod().when(fourSquareActivity.searchText).getText();
        fourSquareActivity.searchText.setText(testedValue);
        Assert.assertEquals(testedValue,fourSquareActivity.searchText.getText());
    }

    @Test
    public void testLeaveView() throws Exception {
        fourSquareActivity.onDetachedFromWindow();
        verify(fourSquarePresenter).detach();
    }

    @Test
    public void testReturnToView() throws Exception {
        reset(fourSquarePresenter);
        fourSquareActivity.onAttachedToWindow();
        verify(fourSquarePresenter).attach(fourSquareActivity);
    }


}