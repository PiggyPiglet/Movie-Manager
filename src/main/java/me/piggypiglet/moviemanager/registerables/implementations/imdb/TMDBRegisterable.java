package me.piggypiglet.moviemanager.registerables.implementations.imdb;

import com.google.inject.Inject;
import com.google.inject.TypeLiteral;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.Config;
import me.piggypiglet.moviemanager.imdb.Manager;
import me.piggypiglet.moviemanager.imdb.implementations.TmdbMovieManager;
import me.piggypiglet.moviemanager.objects.Movie;
import me.piggypiglet.moviemanager.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TMDBRegisterable extends Registerable {
    @Inject @Config private FileConfiguration config;

    @Override
    protected void execute() {
//        addBinding(new TmdbApi(config.getString("tmdb.api-key")));
        addTypeLiteral(new TypeLiteral<Manager<Movie>>() {}, TmdbMovieManager.class);
    }
}
