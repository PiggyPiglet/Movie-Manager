package me.piggypiglet.moviemanager.http.routes.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.http.routes.Route;
import me.piggypiglet.moviemanager.mysql.implementations.MoviesTable;
import me.piggypiglet.moviemanager.objects.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TestRoute extends Route {
    @Inject private MoviesTable moviesTable;

    public TestRoute() {
        super("test");
    }

    @Override
    protected String provide(Map<String, List<String>> params) {
        CompletableFuture<List<Movie>> futureMovies = moviesTable.getMovies();

        //noinspection StatementWithEmptyBody
        while (!futureMovies.isDone()) {}

        List<Movie> movies = new ArrayList<>();

        try {
            movies = futureMovies.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return gson.toJson(movies);
    }
}
