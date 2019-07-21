package me.piggypiglet.moviemanager.mysql.implementations;

import me.piggypiglet.moviemanager.mysql.Table;
import me.piggypiglet.moviemanager.objects.Movie;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MoviesTable extends Table {
    protected MoviesTable() {
        super("data");
    }

    public void addMovie(Movie movie) {
        create().keys("title", "img", "desc").values(movie.getTitle(), movie.getImg(), movie.getDescription()).execute();
    }
}
