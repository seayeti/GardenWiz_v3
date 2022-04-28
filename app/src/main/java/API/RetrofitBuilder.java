package API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {


    private static final String BASE_URL = "https://api.nohles.dev/api/";
    private static Retrofit instance;
    private RetrofitBuilder(){}

    public static Retrofit getInstance() {

        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return instance;

    }
}
