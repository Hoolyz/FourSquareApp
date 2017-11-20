
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


    public Venue(String name, String Address, int Distance)
    {
        this.name = name;
        Address = getLocation().getAddress();
        Distance = getLocation().getDistance();
    }

}
