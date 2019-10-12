package com.sunshine.first.bean;

import java.io.Serializable;

public class OwnerVerifyBean implements Serializable{
    private int community_id,building_id,unitdoor_id,floors_id,houses_id;
    private String token;

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public int getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(int building_id) {
        this.building_id = building_id;
    }

    public int getUnitdoor_id() {
        return unitdoor_id;
    }

    public void setUnitdoor_id(int unitdoor_id) {
        this.unitdoor_id = unitdoor_id;
    }

    public int getFloors_id() {
        return floors_id;
    }

    public void setFloors_id(int floors_id) {
        this.floors_id = floors_id;
    }

    public int getHouses_id() {
        return houses_id;
    }

    public void setHouses_id(int houses_id) {
        this.houses_id = houses_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
