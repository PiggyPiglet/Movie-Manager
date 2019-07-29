package me.piggypiglet.moviemanager.registerables.implementations.imdb;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.imdb.Manager;
import me.piggypiglet.moviemanager.registerables.Registerable;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ManagersRegisterable extends Registerable {
    @Inject private Reflections reflections;

    @Override
    protected void execute() {
        List<String> movies = Arrays.asList("scorch trials", "hunger games");
        reflections.getSubTypesOf(Manager.class).stream().map(injector::getInstance).forEach(m -> m.setup(movies));
    }
}
