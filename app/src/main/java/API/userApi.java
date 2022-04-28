package API;
import retrofit2.Call;
import retrofit2.http.GET;

public interface userApi {
    //fix
    @GET("post/read_single.php?sensor_id=2")
    Call<userData> getData();


}


