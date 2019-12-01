package com.dicoding.moviecatalogsubmission.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ValueMovies {

    @SerializedName("page")
    private Long mPage;
    @SerializedName("results")
    private List<ResultMovies> mMovieResults;
    @SerializedName("total_pages")
    private Long mTotalPages;
    @SerializedName("total_results")
    private Long mTotalResults;

    public Long getmPage() {
        return mPage;
    }

    public void setmPage(Long mPage) {
        this.mPage = mPage;
    }

    public List<ResultMovies> getmMovieResults() {
        return mMovieResults;
    }

    public void setmMovieResults(List<ResultMovies> mMovieResults) {
        this.mMovieResults = mMovieResults;
    }

    public Long getmTotalPages() {
        return mTotalPages;
    }

    public void setmTotalPages(Long mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public Long getmTotalResults() {
        return mTotalResults;
    }

    public void setmTotalResults(Long mTotalResults) {
        this.mTotalResults = mTotalResults;
    }
}
