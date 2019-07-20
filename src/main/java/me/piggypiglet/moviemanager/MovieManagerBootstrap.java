package me.piggypiglet.moviemanager;

import com.google.inject.Injector;
import me.piggypiglet.moviemanager.guice.modules.InitialModule;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MovieManagerBootstrap {
    private MovieManagerBootstrap() {
        Injector injector = new InitialModule().createInjector();
        injector.getInstance(MovieManager.class).start(injector);
    }

    public static void main(String[] args) {
        new MovieManagerBootstrap();
    }
}
