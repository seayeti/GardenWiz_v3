package API;

public class sensorData {

    public int getSensor_id() {
        return sensor_id;
    }

    public double getTemp() { return Math.round(temp*100.00)/100.00;    }

    public double getHumidity() {
        return Math.round(humidity*100.00)/100.00;
    }

    public double getLight() {
        return Math.round(light*100.00)/100.00;
    }

    public double getRain() {
        return Math.round(rain*100.00)/100.00;
    }

    public double getMoisture() {
        return Math.round(moisture*100.00)/100.00;
    }

    public String getSensor_datetime() {
        return sensor_datetime;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public double getPh() {
        return Math.round(PH*100.00)/100.00;
    }

    public int getRunID() {
        return runID;
    }

    //template for data we are using parsing
    private int sensor_id;
    private double temp;
    private double humidity;
    private double light;
    private double rain;
    private double moisture;
    private double PH;
    private String sensor_datetime;
    private String macAddress;
    private int runID;
}