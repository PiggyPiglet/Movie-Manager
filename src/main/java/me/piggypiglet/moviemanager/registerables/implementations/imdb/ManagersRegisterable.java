package me.piggypiglet.moviemanager.registerables.implementations.imdb;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.imdb.implementations.TmdbMovieManager;
import me.piggypiglet.moviemanager.registerables.Registerable;

import java.util.Arrays;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ManagersRegisterable extends Registerable {
//    @Inject private Reflections reflections;
//    @Inject private Injector injector;
    @Inject private TmdbMovieManager movieManager;

    @Override
    protected void execute() {
        List<String> movies = Arrays.asList("scorch trials", "maze runner");
        movieManager.setup(movies);
    }
}
