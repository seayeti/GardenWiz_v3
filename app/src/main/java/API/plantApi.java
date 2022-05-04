package API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface plantApi {
    @GET("Plantdb/search.php")
    Call<List<plantData>> getData(@Header("Authorization") String token,
                                  @Query("TemperatureMinimum") double TemperatureMinimum,
                                  @Query("moisture") String moisture,
                                  @Query("Shade") String Shade,
                                  @Query("PH") String ph,
                                  @Query("season") String season,
                                  @Query("State") String State);

    @GET("Plantdb/read_single.php")
    Call<List<plantData>> getData(@Header("Authorization") String token,
                                  @Query("CommonName") String CommonName);

    @GET("Plantdb/singlePlant.php")
    Call<plantData> getplantData(@Header("Authorization") String token,
                                 @Query("CommonName") String CommonName);

    @GET("Plantdb/singlePlantID.php")
    Call<plantData> getBETYData(@Header("Authorization") String token,
                                @Query("betydbspeciesid") int betydbspeciesid);

    @GET("Plantdb/searchAdv.php")
    Call<List<plantData>> getadvData(@Header("Authorization") String token,
                                     @Query("TemperatureMinimum") double TemperatureMinimum,
                                     @Query("moisture") String moisture,
                                     @Query("Shade") String Shade,
                                     @Query("PH") String ph,
                                     @Query("GrowthHabit") String GrowthHabit,
                                     @Query("season") String season,
                                     @Query("BloomPeriod") String BloomPeriod,
                                     @Query("State") String State,
                                     @Query("CommercialAvailability") String CommercialAvailability,
                                     @Query("DroughtTolerance") String DroughtTolerance,
                                     @Query("PalatableHuman") String PalatableHuman);

    @GET("Plantdb/searchPlants.php")
    Call<List<plantData>> getsearchPlants(@Header("Authorization") String token,
                                          @Query("CommonName") String CommonName,
                                          @Query("GrowthHabit") String GrowthHabit,
                                          @Query("season") String season,
                                          @Query("BloomPeriod") String BloomPeriod,
                                          @Query("State") String State,
                                          @Query("CommercialAvailability") String CommercialAvailability,
                                          @Query("DroughtTolerance") String DroughtTolerance,
                                          @Query("PalatableHuman") String PalatableHuman);

    @GET("Plantdb/searchPlantsMin.php")
    Call<List<plantData>> getsearchPlantsMin(@Header("Authorization") String token,
                                             @Query("CommonName") String CommonName,
                                             @Query("GrowthHabit") String GrowthHabit,
                                             @Query("season") String season,
                                             @Query("BloomPeriod") String BloomPeriod,
                                             @Query("State") String State,
                                             @Query("CommercialAvailability") String CommercialAvailability,
                                             @Query("DroughtTolerance") String DroughtTolerance,
                                             @Query("PalatableHuman") String PalatableHuman);

    @GET("runs/read.php")
    Call<List<runsData>> getrunsData(@Header("Authorization") String token,
                                     @Query("userID") int userID);

    @GET("results/searchRun.php")
    Call<List<resultsData>> getresultsData(@Header("Authorization") String token,
                                           @Query("runID") int runID);

    @GET("api.php")
    Call<PlantImages> getplantImages(@Query("action") String action,
                                     @Query("format") String format,
                                     @Query("prop") String prop,
                                     @Query("titles") String titles,
                                     @Query("formatversion") String formatversion,
                                     @Query("pithumbsize") String pithumbsize);

}