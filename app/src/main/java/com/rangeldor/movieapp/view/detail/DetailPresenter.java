package com.rangeldor.movieapp.view.detail;

import android.util.Log;

import com.rangeldor.movieapp.Utils;
import com.rangeldor.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {

    private static final String TAG = "DetailPresenter";
    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMovieById(String movie_id, String page, String language) {
        view.showLoading ( );

        Utils.getApi ( ).getMovieById ( movie_id, page, language ).enqueue ( new Callback<Movie.Result> ( ) {
            @Override
            public void onResponse(Call<Movie.Result> call , Response<Movie.Result> response) {
                view.hideLoading ( );

                if ( response.isSuccessful ( ) && response.body ( ) != null ) {
                    view.setResult (  response.body ( ) );
                } else {
                    view.onErrorLoading ( response.message ( ) );
                }
            }

            @Override
            public void onFailure(Call<Movie.Result> call , Throwable t) {
                view.hideLoading ( );
                view.onErrorLoading ( t.getLocalizedMessage ( ) );
            }
        } );
    }

    void getMovieCastsById(String movie_id, String page, String language){
        view.showLoading ( );

        Utils.getApi ( ).getMovieCastsById (  movie_id, page, language ).enqueue ( new Callback<Movie.Detail> ( ) {
            @Override
            public void onResponse(Call<Movie.Detail> call , Response<Movie.Detail> response) {
                view.hideLoading ( );

                if ( response.isSuccessful ( ) && response.body ( ) != null ) {
                    view.setDetail ( response.body ( ));
                } else {
                    view.onErrorLoading ( response.message ( ) );
                }
            }

            @Override
            public void onFailure(Call<Movie.Detail> call , Throwable t) {
                view.hideLoading ( );
                view.onErrorLoading ( t.getLocalizedMessage ( ) );
            }
        } );
    }
}
