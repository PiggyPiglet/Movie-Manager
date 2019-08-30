package me.piggypiglet.moviemanager.http;

import com.google.inject.Inject;
import me.piggypiglet.framework.http.responses.Route;
import me.piggypiglet.moviemanager.managers.MovieManager;

import java.util.List;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MoviesRoute extends Route {
    @Inject private MovieManager movieManager;

    public MoviesRoute() {
        super("movies");
    }

    @Override
    protected String provide(Map<String, List<String>> params) {
        if (params.containsKey("search")) {
            return gson.toJson(movieManager.search(params.get("search").get(0)));
        }

        return gson.toJson(movieManager.getAll());
    }
}