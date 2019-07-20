package me.piggypiglet.moviemanager.guice.modules;

import com.google.inject.*;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class InitialModule extends AbstractModule {
    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    @Provides
    @Singleton
    public Reflections providesReflections() {
        return new Reflections("me.piggypiglet.moviemanager");
    }
}