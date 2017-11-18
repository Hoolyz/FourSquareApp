package io.sirv.olli.foursquareapp.presenter;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import io.sirv.olli.foursquareapp.Base.BaseActivity;
import io.sirv.olli.foursquareapp.R;
import io.sirv.olli.foursquareapp.presenter.model.Venue;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public class FourSquareActivity extends BaseActivity<FourSquarePresenter> implements FourSquarecontract.View {



    private ListView listView;

    private EditText searchText;


    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }



    @Override
    protected void init(Bundle savedInstanceState) {



        listView = findViewById(R.id.listView);

        searchText = findViewById(R.id.searchField);

/*        fourSquarePresenter = new FourSquarePresenter();
        fourSquarePresenter.attach(this);
*/
        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
               getPresenter().onTextChanged(s);
            }
        });

    }

    @Override
    public void injectDependencies() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onListViewPopulate(List<Venue> venues){

        for(Venue testi : venues){

            Log.i("TAMA ON ACTIVITY",testi.getName() + testi.getLocation().getAddress() + testi.getLocation().getDistance());

        }

        ArrayAdapter<Venue> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, venues);



        listView.setAdapter(arrayAdapter);

    }




}

