package me.piggypiglet.moviemanager.http.routes.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.http.routes.Route;
import me.piggypiglet.moviemanager.imdb.implementations.TmdbMovieManager;

import java.util.List;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MoviesRoute extends Route {
    @Inject private TmdbMovieManager tmdbMovieManager;

    public MoviesRoute() {
        super("movies");
    }

    @Override
    protected String provide(Map<String, List<String>> params) {
//        CompletableFuture<List<Movie>> futureMovies = moviesTable.getMovies();
//
//        //noinspection StatementWithEmptyBody
//        while (!futureMovies.isDone()) {}
//
//        List<Movie> movies = new ArrayList<>();
//
//        try {
//            movies = futureMovies.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return gson.toJson(movies);

        return gson.toJson(tmdbMovieManager.getAll().values());
    }
}
