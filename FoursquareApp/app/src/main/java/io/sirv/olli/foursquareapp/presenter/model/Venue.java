
package io.sirv.olli.foursquareapp.presenter.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {

        if (this.getLocation().getAddress() == "null"){
            return "Name:" + this.name + " Distance: " + this.getLocation().getDistance() + "";
        }
        else {

            return "Name:" + this.name + " Address: " + this.getLocation().getAddress() + " Distance: " + this.getLocation().getDistance() + "";

    }
}}
