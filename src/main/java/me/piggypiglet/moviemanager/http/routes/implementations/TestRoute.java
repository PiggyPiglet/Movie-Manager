package me.piggypiglet.moviemanager.http.routes.implementations;

import com.google.gson.JsonObject;
import me.piggypiglet.moviemanager.http.routes.Route;

import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class TestRoute extends Route {
    public TestRoute() {
        super("test");
    }

    @Override
    protected JsonObject provide(Map<String, String> params) {
        return null;
    }
}
