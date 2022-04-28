package API;

public class runsData {
    private int runID;
    private int userID;
    private String timeStarted;
    private int timeToEnd;
    private String runName;
    private String MacAddress;

    public runsData(int i, int i1, String nameInput, String s2) {
        this.setUserID(i);
        this.setTimeToEnd(i1);
        this.setRunName(nameInput);
        this.setMacAddress(s2);


    }

    public int getRunID() {
        return runID;
    }

    public void setRunID(int runID) {
        this.runID = runID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(String TimeStarted) {
        timeStarted = TimeStarted;
    }

    public int getTimeToEnd() {
        return timeToEnd;
    }

    public void setTimeToEnd(int timeToEnd) {
        this.timeToEnd = timeToEnd;
    }

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }





}