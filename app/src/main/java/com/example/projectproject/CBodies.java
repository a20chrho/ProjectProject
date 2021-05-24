package com.example.projectproject;

import com.google.gson.annotations.SerializedName;

public class CBodies {

    @SerializedName("ID")
    private String id;
    private String name;
    @SerializedName("location")
    private String gravity;
    @SerializedName("size")
    private String radius;
    @SerializedName("cost")
    private String volume;
    private Auxdata auxdata;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGravity() {
        return gravity;
    }

    public String getRadius() {
        return radius;
    }

    public String getVolume() {
        return volume;
    }
    public Auxdata getAuxdata() {
        return auxdata;
    }

    @Override
    public String toString() { return name+"\n\n"+"Description: "+auxdata.getDescription()+"\n\n"+
            "volume: "+volume+"km3\nradius: "+radius+"km,\ngravity: "+gravity+"\n\n"
        +"Zombie population: "+auxdata.getZombie()+".\n";}
}

