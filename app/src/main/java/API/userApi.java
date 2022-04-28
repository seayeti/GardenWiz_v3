package API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface userApi {
    //fix
    @GET("post/read_single.php?sensor_id=2")
    Call<userData> getData();

    @GET("user/login.php")
    Call<login> login(@Query("email") String email,
                      @Query("password") String password);

}


