package me.piggypiglet.moviemanager.managers.implementations;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.managers.Manager;
import me.piggypiglet.moviemanager.mysql.implementations.MoviesTable;
import me.piggypiglet.moviemanager.objects.Movie;

import java.util.ArrayList;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class MovieManager extends Manager<Movie> {
    @Inject @Config private FileConfiguration config;

    protected MovieManager() {
        super(new MoviesTable());
    }

    @Override
    protected List<Movie> populate(String parentPath, List<String> data) {
        List<Movie> movies = new ArrayList<>();
        TmdbSearch search = new TmdbApi(config.getString("tmdb.api-key")).getSearch();

        for (String datum : data) {
            List<MovieDb> foundMovies = search.searchMovie(datum, null, null, true, null).getResults();

            if (foundMovies.size() > 0) {
                MovieDb movieDb = foundMovies.get(0);
                movies.add(new Movie(movieDb.getTitle(), "https://image.tmdb.org/t/p/original" + movieDb.getPosterPath(), movieDb.getOverview(), "null"));
            }
        }

        return movies;
    }
}
