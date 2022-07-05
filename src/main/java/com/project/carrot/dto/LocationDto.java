package com.project.carrot.dto;

public class LocationDto {

    private String cityName;
    private String districtName;
    private String TownName;

    public LocationDto() {}

    public LocationDto(String cityName, String districtName, String townName) {
        this.cityName = cityName;
        this.districtName = districtName;
        TownName = townName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getTownName() {
        return TownName;
    }

    public void setTownName(String townName) {
        TownName = townName;
    }
}
