package me.piggypiglet.moviemanager.mysql.implementations;

import me.piggypiglet.moviemanager.mysql.Table;
import me.piggypiglet.moviemanager.objects.Movie;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MoviesTable extends Table {
    protected MoviesTable() {
        super("data");
    }

    public void addMovie(Movie movie) {
        create().keys("title", "img", "desc").values(movie.getTitle(), movie.getImg(), movie.getDescription()).build().execute();
    }

    public CompletableFuture<List<Movie>> getMovies() {
        return getter().build().getAll().thenApply(rows -> rows.stream().map(r -> new Movie(r.getString("title"), r.getString("img"), r.getString("desc"))).collect(Collectors.toList()));
    }
}
