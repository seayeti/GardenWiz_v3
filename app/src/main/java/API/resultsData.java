package API;


public class resultsData {

    private int resultsID;
    private int runID;
    private static int betydbspeciesid;
    private String type,symbol;
    private String season;
    private String state;
    private String bloomPeriod;
    private String commAvail;
    private String droughtTol;
    private String edible, shadeTol, flowerColor;
    private String CommonName;
    private double  phMin,phMax;



    public int getResultsID() {
        return resultsID;
    }

    public void setResultsID(int resultsID) {
        this.resultsID = resultsID;
    }

    public int getRunID() {
        return runID;
    }

    public void setRunID(int runID) {
        this.runID = runID;
    }

    public static int getBetyID() {
        return betydbspeciesid;
    }

    public void setBetyID(int betyID) {
        betydbspeciesid = betyID;
    }


    public String getCommonName() {
        return CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
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