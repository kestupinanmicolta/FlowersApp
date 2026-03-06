package com.flowersapp.data.model;

import java.io.Serializable;

public class City implements Serializable {
    private int id;
    private String name;
    private String description;
    private Integer surface;
    private Integer population;
    private String postalCode;
    private int departmentId;
    private Object department;
    private Object touristAttractions;
    private Object presidents;
    private Object indigenousReservations;
    private Object airports;
    private Object radios;

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

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public Object getTouristAttractions() {
        return touristAttractions;
    }

    public void setTouristAttractions(Object touristAttractions) {
        this.touristAttractions = touristAttractions;
    }

    public Object getPresidents() {
        return presidents;
    }

    public void setPresidents(Object presidents) {
        this.presidents = presidents;
    }

    public Object getAirports() {
        return airports;
    }

    public void setAirports(Object airports) {
        this.airports = airports;
    }

    public Object getIndigenousReservations() {
        return indigenousReservations;
    }

    public void setIndigenousReservations(Object indigenousReservations) {
        this.indigenousReservations = indigenousReservations;
    }

    public Object getRadios() {
        return radios;
    }

    public void setRadios(Object radios) {
        this.radios = radios;
    }
    @Override
    public String toString() {
        return name; // Esto es lo que el Spinner dibujará en pantalla
    }
}
