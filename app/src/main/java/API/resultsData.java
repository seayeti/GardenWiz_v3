package API;


public class resultsData {

    private int resultsID;
    private int runID;
    private static int betydbspeciesid;
    private String CommonName;


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


}