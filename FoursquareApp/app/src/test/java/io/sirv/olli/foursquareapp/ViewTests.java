package io.sirv.olli.foursquareapp;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import io.sirv.olli.foursquareapp.presenter.CustomAdapter;
import io.sirv.olli.foursquareapp.presenter.FourSquareActivity;
import io.sirv.olli.foursquareapp.presenter.FourSquarePresenter;
import io.sirv.olli.foursquareapp.presenter.FourSquarecontract;
import io.sirv.olli.foursquareapp.presenter.model.LocationHelpers;

import io.sirv.olli.foursquareapp.presenter.model.Venue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Olli-Pekka on 20/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)

public class ViewTests {

    @Mock
    private FourSquarecontract.View fqView;

    FourSquarePresenter fourSquarePresenter;

    @Mock
    FourSquareActivity fourSquareActivity;

    @Mock
    FourSquarePresenter fourSquarePresenterMock;


    Context context = mock(Context.class);

    public List<Venue> Mock_Venue = new ArrayList<>();


    @Captor
    private ArgumentCaptor<List<Venue>> mockVenueItems;


    private void generateMockVenueItem() {
        Venue venue = new Venue("Testi","TestiKuja 1",300);
        Venue venue2 = new Venue("Testi","TestiKuja 1",300);
        Mock_Venue.add(venue);
        Mock_Venue.add(venue2);
    }

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
       fourSquarePresenter = new FourSquarePresenter();
       fourSquarePresenter.attach(fqView);

       generateMockVenueItem();

    }

    @Test
    public void urlTest() throws Exception {

        fourSquarePresenterMock.getFourSquare("testi","60.1705171,24.935404","20172011");
        verify(fourSquarePresenterMock).getFourSquare("testi","60.1705171,24.935404","20172011");

    }

    @Test
    public void listViewPopulate() throws Exception {

        fqView.onListViewPopulate(Mock_Venue);
        verify(fqView).onListViewPopulate(mockVenueItems.capture());
        assert(mockVenueItems.getValue().equals(Mock_Venue));

    }

    @Test
    public void testInput() throws Exception {

        fourSquareActivity = new FourSquareActivity();

        LocationHelpers locationHelpers = new LocationHelpers();

        locationHelpers.LocationActivity = fourSquareActivity;

        locationHelpers.LocationContext = context;

        fourSquarePresenterMock.onTextChanged("Testi",locationHelpers);

        verify(fourSquarePresenterMock).onTextChanged("Testi",locationHelpers);


    }




}