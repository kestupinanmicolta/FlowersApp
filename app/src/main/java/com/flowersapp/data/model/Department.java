package com.flowersapp.data.model;

import java.io.Serializable;

public class Department implements Serializable {
    private int id;
    private String name;
    private String description;
    private int cityCapitalId;
    private int municipalities;
    private int surface;
    private int population;
    private String phonePrefix;
    private int countryId;
    private City cityCapital;
    private Object country;
    private Object cities;
    private int regionId;
    private Object region;
    private Object naturalAreas;
    private Object maps;
    private Object indigenousReservations;
    private Object airports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCityCapitalId() {
        return cityCapitalId;
    }

    public void setCityCapitalId(int cityCapitalId) {
        this.cityCapitalId = cityCapitalId;
    }

    public int getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(int municipalities) {
        this.municipalities = municipalities;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public City getCityCapital() {
        return cityCapital;
    }

    public void setCityCapital(City cityCapital) {
        this.cityCapital = cityCapital;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getCities() {
        return cities;
    }

    public void setCities(Object cities) {
        this.cities = cities;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

    public Object getNaturalAreas() {
        return naturalAreas;
    }

    public void setNaturalAreas(Object naturalAreas) {
        this.naturalAreas = naturalAreas;
    }

    public Object getMaps() {
        return maps;
    }

    public void setMaps(Object maps) {
        this.maps = maps;
    }

    public Object getIndigenousReservations() {
        return indigenousReservations;
    }

    public void setIndigenousReservations(Object indigenousReservations) {
        this.indigenousReservations = indigenousReservations;
    }

    public Object getAirports() {
        return airports;
    }

    public void setAirports(Object airports) {
        this.airports = airports;
    }
    @Override
    public String toString() {
        return name; // Esto es lo que el Spinner dibujará en pantalla
    }
}
