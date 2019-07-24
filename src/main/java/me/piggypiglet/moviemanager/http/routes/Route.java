package me.piggypiglet.moviemanager.http.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Route {
    protected final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private final String route;

    protected Route(String route) {
        this.route = route;
    }

    protected abstract JsonObject provide(Map<String, String> params);

    public JsonObject run(Map<String, String> params) {
        return provide(params);
    }
}
