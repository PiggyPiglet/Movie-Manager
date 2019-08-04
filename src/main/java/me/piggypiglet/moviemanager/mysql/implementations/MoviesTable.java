package me.piggypiglet.moviemanager.mysql.implementations;

import me.piggypiglet.moviemanager.mysql.Table;
import me.piggypiglet.moviemanager.mysql.components.row.objects.Location;
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
        create().keys("title", "og_title", "img", "desc", "path").values(movie.getTitle(), movie.getOgTitle(), movie.getImg(), movie.getDescription(), movie.getPath()).build().execute();
    }

    @Override
    public CompletableFuture<List<Movie>> getAll() {
        return getter().build().getAll().thenApply(rows -> rows.stream().map(r -> new Movie(r.getString("title"), r.getString("og_title"), r.getString("img"), r.getString("desc"), r.getString("path"))).collect(Collectors.toList()));
    }

    @Override
    public void save(Movie movie) {
        Location location = Location.builder().key("og_title").value(movie.getOgTitle()).build();

        if (!getter().locations(location).build().exists()) {
            addMovie(movie);
        }
    }
}
