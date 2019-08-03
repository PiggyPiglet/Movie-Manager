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
public final class MoviesTable extends Table<Movie> {
    public MoviesTable() {
        super("data");
    }

    public void addMovie(Movie movie) {
        create().keys("title", "img", "desc", "url").values(movie.getTitle(), movie.getImg(), movie.getDescription(), movie.getUrl()).build().execute();
    }

    @Override
    public CompletableFuture<List<Movie>> getAll() {
        return getter().build().getAll().thenApply(rows -> rows.stream().map(r -> new Movie(r.getString("title"), r.getString("img"), r.getString("desc"), r.getString("url"))).collect(Collectors.toList()));
    }
}
