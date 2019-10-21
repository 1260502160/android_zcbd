package com.sunshine.first.bean;

import java.io.Serializable;

public class VisitorBean implements Serializable{
    String time;
    String visi_name;
    String visi_mobile;
    String car_num;
    String unitdoor_id;
    String floors_id;
    String houses_id;
    String building_id;
    String community_id;
    String visi_imgs;
    String token;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVisi_name() {
        return visi_name;
    }

    public void setVisi_name(String visi_name) {
        this.visi_name = visi_name;
    }

    public String getVisi_mobile() {
        return visi_mobile;
    }

    public void setVisi_mobile(String visi_mobile) {
        this.visi_mobile = visi_mobile;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getUnitdoor_id() {
        return unitdoor_id;
    }

    public void setUnitdoor_id(String unitdoor_id) {
        this.unitdoor_id = unitdoor_id;
    }

    public String getFloors_id() {
        return floors_id;
    }

    public void setFloors_id(String floors_id) {
        this.floors_id = floors_id;
    }

    public String getHouses_id() {
        return houses_id;
    }

    public void setHouses_id(String houses_id) {
        this.houses_id = houses_id;
    }

    public String getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(String building_id) {
        this.building_id = building_id;
    }

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getVisi_imgs() {
        return visi_imgs;
    }

    public void setVisi_imgs(String visi_imgs) {
        this.visi_imgs = visi_imgs;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
