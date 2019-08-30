package me.piggypiglet.moviemanager.registerables;

import com.google.inject.Inject;
import info.movito.themoviedbapi.TmdbApi;
import me.piggypiglet.framework.file.framework.FileConfiguration;
import me.piggypiglet.framework.registerables.StartupRegisterable;
import me.piggypiglet.framework.utils.annotations.files.Config;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TMDBRegisterable extends StartupRegisterable {
    @Inject @Config private FileConfiguration config;

    @Override
    protected void execute() {
        addBinding(new TmdbApi(config.getString("tmdb.api-key")));
    }
}
