package me.piggypiglet.moviemanager.registerables.implementations.tmdb;

import com.google.inject.Inject;
import info.movito.themoviedbapi.TmdbApi;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TMDBRegisterable extends Registerable {
    @Inject @Config private FileConfiguration config;

    @Override
    protected void execute() {
        addBinding(new TmdbApi(config.getString("tmdb.api-key")));
    }
}