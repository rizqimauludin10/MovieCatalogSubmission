package com.dicoding.moviecatalogsubmission.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ValueMovies {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<ResultMovies> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ValueMovies() {
    }

    /**
     *
     * @param totalResults
     * @param totalPages
     * @param page
     * @param results
     */
    public ValueMovies(Integer page, Integer totalResults, Integer totalPages, List<ResultMovies> results) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<ResultMovies> getResults() {
        return results;
    }

    public void setResults(List<ResultMovies> results) {
        this.results = results;
    }

}
