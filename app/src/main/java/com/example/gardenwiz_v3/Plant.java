package com.example.gardenwiz_v3;

public class Plant {
    private String name;
    private String type,symbol;
    private String season;
    private String state;
    private String bloomPeriod;
    private String commAvail;
    private String droughtTol;
    private String edible, shadeTol, flowerColor;
    private double  phMin,phMax;

    public Plant(String nam, String typ, String seas, String bloomP, String stat, String commA, String droughtT, String edib, double phmin, double phmax, String shadeT, String flowerClr, String sym){
        name = nam;
        type = typ;
        season = seas;
        bloomPeriod = bloomP;
        state = stat;
        commAvail = commA;
        droughtTol = droughtT;
        edible = edib;
        phMin = phmin;
        phMax = phmax;
        shadeTol = shadeT;
        flowerColor = flowerClr;
        symbol = sym;
    }

    public String getEdible() {
        return edible;
    }
    public void setEdible(String edible) {
        this.edible = edible;
    }

    public String getSeason() {
        return season;
    }
    public void setSeason(String season) {
        this.season = season;
    }


    public String getDroughtTol() {
        return droughtTol;
    }
    public void setDroughtTol(String droughtTol) {
        this.droughtTol = droughtTol;
    }

    public String getCommAvail() {
        return commAvail;
    }
    public void setCommAvail(String commAvail) {
        this.commAvail = commAvail;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBloomPeriod() {
        return bloomPeriod;
    }
    public void setBloomPeriod(String bloomPeriod) {
        this.bloomPeriod = bloomPeriod;
    }

    public double getPhMin() {
        return phMin;
    }
    public void setPhMin(double phMin) {
        this.phMin = phMin;
    }

    public double getPhMax() {
        return phMax;
    }
    public void setPhMax(double phMax) {
        this.phMax = phMax;
    }

    public String getShadeTol() {
        return shadeTol;
    }
    public void setShadeTol(String shadeTol) {
        this.shadeTol = shadeTol;
    }

    public String getFlowerColor() {
        return flowerColor;
    }
    public void setFlowerColor(String flowerColor) {
        this.flowerColor = flowerColor;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}