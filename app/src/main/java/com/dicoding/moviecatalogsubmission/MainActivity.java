package com.dicoding.moviecatalogsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ListView;

import com.dicoding.moviecatalogsubmission.adapter.MovieAdapter;
import com.dicoding.moviecatalogsubmission.model.Movie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private String[] dataTittle;
    private String[] dataDesc;
    private TypedArray dataPoster;
    private ArrayList<Movie> movieArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lvList);
        movieAdapter = new MovieAdapter(this);
        listView.setAdapter(movieAdapter);

        prepare();
        addItem();

    }

    private void addItem(){
        movieArrayList = new ArrayList<>();

        for (int i = 0; i < dataTittle.length; i++){
            Movie movie = new Movie();
            movie.setMoviePoster(dataPoster.getResourceId(i, -1));
            movie.setMovieTittle(dataTittle[i]);
            movie.setMovieDesc(dataDesc[i]);
            movieArrayList.add(movie);
        }

        movieAdapter.setMovies(movieArrayList);
    }

    private void prepare() {
        dataTittle = getResources().getStringArray(R.array.data_name);
        dataDesc = getResources().getStringArray(R.array.data_description);
        dataPoster = getResources().obtainTypedArray(R.array.data_photo);

    }
}
