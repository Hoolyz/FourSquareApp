package io.sirv.olli.foursquareapp.presenter.Services;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.sirv.olli.foursquareapp.R;
import io.sirv.olli.foursquareapp.presenter.model.Venue;

/**
 * Created by Olli-Pekka on 19/11/2017.
 */

public class CustomAdapter extends ArrayAdapter<Venue> {


    /*

    Creates custom adapter for Listview using Venue class.

     */

    private Context mContext;
    private List<Venue> venueList = new ArrayList<>();

    public CustomAdapter(@NonNull Context context, List<Venue> list) {
        super(context, 0 , list);
        mContext = context;
        venueList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_adapter,parent,false);

        Collections.sort(venueList, new Comparator<Venue>() {
            @Override
            public int compare(Venue venue1, Venue venue2) {


                return venue1.getLocation().getDistance().compareTo(venue2.getLocation().getDistance());
            }
        });


        final Venue currentVenue = venueList.get(position);



        TextView name = (TextView)listItem.findViewById(R.id.name);
        name.setText(currentVenue.getName());

        TextView address = (TextView) listItem.findViewById(R.id.address);
        address.setText(currentVenue.getLocation().getAddress());

        TextView distance = (TextView) listItem.findViewById(R.id.distance);
        distance.setText(currentVenue.getLocation().getDistance().toString() + "m");

        return listItem;


    }

}
