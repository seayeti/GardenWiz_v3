package API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImages {




        private static final String BASE_URL = "https://en.wikipedia.org/w/";
        private static Retrofit instance;
        private RetrofitImages(){}

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

