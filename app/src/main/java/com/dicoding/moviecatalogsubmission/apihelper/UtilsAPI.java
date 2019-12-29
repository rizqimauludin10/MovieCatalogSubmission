package com.dicoding.moviecatalogsubmission.apihelper;

import com.dicoding.moviecatalogsubmission.BuildConfig;

public class UtilsAPI {

    private static final String BASE_URL_API = BuildConfig.PATH_API_KEY;

    public static BaseAPIService getApiService() {
        return RetrofitClient.getRetrofit(BASE_URL_API).create(BaseAPIService.class);
    }
}
