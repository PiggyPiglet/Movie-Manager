package me.piggypiglet.moviemanager;

import me.piggypiglet.framework.Framework;
import me.piggypiglet.framework.utils.annotations.files.Config;
import me.piggypiglet.framework.utils.annotations.registerable.RegisterableData;
import me.piggypiglet.moviemanager.registerables.TMDBRegisterable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MovieManager {
    private MovieManager() {
        Framework.builder()
                .main(this)
                .pckg("me.piggypiglet.moviemanager")
                .commandPrefix("!")
                .startup(new RegisterableData(TMDBRegisterable.class))
                .file(true, "config", "/config.json", "./config.json", Config.class)
                .build()
                .init();
    }

    public static void main(String[] args) {
        new MovieManager();
    }
}
