
package io.sirv.olli.foursquareapp.presenter.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    /*

    Api response classes

     */

    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("address")
    @Expose
    private String address;


    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
