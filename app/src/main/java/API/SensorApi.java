package API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SensorApi {

    @GET("sensor/read_single.php?sensor_id=2")
    Call<sensorData> getData();

    @POST("runs/create.php")
    Call<runsData> createRun(@Query("userID") int userID,
                             @Query("timeToEnd") int timeToEnd,
                             @Query("runName")String runName,
                             @Query("macAddress") String macAddress);

    @POST("results/create.php")
    Call<resultsData> createResult(@Query("runID") int runID,
                                   @Query("betydbspeciesid") String betydbspeciesid);


    @POST("runs/update.php")
    Call<runsData> updateRun(@Query("runName")String runName,
                             @Query("runID") int runID);

    @GET("runs/readRunID.php")
    Call<runsData> getRunID(@Query("userID") int userID);

    @GET("sensor/getSensorData.php")
    Call<sensorData> getSensorData(@Query("runID") int runID);

}