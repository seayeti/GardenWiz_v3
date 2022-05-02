package API;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface userApi {
    //fix
    @GET("post/read_single.php?sensor_id=2")
    Call<userData> getData(@Header("Authorization") String token);

    @GET("user/login.php")
    Call<login> login(@Query("email") String email,
                      @Query("password") String password);
    @GET("user/create.php")
    Call<login> register(@Query("email") String email,
                      @Query("password") String password);
    @GET("user/emailCheck.php")
    Call<login> emailCheck(@Query("email") String email);



}


