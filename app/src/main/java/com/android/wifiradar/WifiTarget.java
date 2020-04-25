package com.android.wifiradar;

public class WifiTarget {

    String id, name, strenght;

    public WifiTarget(){}
    public WifiTarget(String id, String name, String stre){

        this.id = id;
        this.name = name;
        this.strenght = stre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrenght() {
        return strenght;
    }

    public void setStrenght(String strenght) {
        this.strenght = strenght;
    }
}
