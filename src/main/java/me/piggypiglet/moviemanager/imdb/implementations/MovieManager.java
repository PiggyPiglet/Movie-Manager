package me.piggypiglet.moviemanager.imdb.implementations;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.Config;
import me.piggypiglet.moviemanager.imdb.Manager;
import me.piggypiglet.moviemanager.objects.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class MovieManager extends Manager<Movie> {
    @Inject @Config private FileConfiguration config;

    @Override
    protected Map<Integer, Movie> populate(List<String> data) {
        Map<Integer, Movie> movies = new HashMap<>();
        TmdbSearch search = new TmdbApi(config.getString("tmdb.api-key")).getSearch();

        for (int i = 0, dataSize = data.size(); i < dataSize; i++) {
            List<MovieDb> foundMovies = search.searchMovie(data.get(i), null, null, true, null).getResults();

            if (foundMovies.size() > 0) {
                MovieDb movieDb = foundMovies.get(0);
                movies.put(i, new Movie(movieDb.getTitle(), "https://image.tmdb.org/t/p/original" + movieDb.getPosterPath(), movieDb.getOverview()));
            }
        }

        return movies;
    }
}
