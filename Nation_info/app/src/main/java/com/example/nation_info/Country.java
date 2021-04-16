package com.example.nation_info;

public class Country {

    String countryName, flag, population, areaInSqKm;


    public Country(String countryName, String flag, String population, String areaInSqKm) {
        this.countryName = countryName;
        this.flag = flag;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(String areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
