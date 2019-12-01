package com.dicoding.moviecatalogsubmission.apihelper;

public class UtilsAPI {

    public static final String BASE_URL_API = "https://api.themoviedb.org/3/";

    public static BaseAPIService getApiService() {
        return RetrofitClient.getRetrofit(BASE_URL_API).create(BaseAPIService.class);
    }
}
