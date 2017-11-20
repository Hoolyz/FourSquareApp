package io.sirv.olli.foursquareapp.presenter.Services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Olli-Pekka on 15/11/2017.
 */

public class ApiDateVersion {

    DateFormat df = new SimpleDateFormat("yyyyMMdd");
    public String currentDate = df.format(new Date());


}
