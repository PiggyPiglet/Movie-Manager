package me.piggypiglet.moviemanager.http.responses;

import com.google.inject.Singleton;
import fi.iki.elonen.NanoHTTPD;
import me.piggypiglet.moviemanager.http.routes.Route;

import java.util.ArrayList;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class ResponseHandler {
    private final List<Route> routes = new ArrayList<>();

    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        final String format = "<html><body><h1>%s</h1></body></html>";

        for (Route route : routes) {
            if (session.getUri().contains(route.getRoute())) {
                return NanoHTTPD.newFixedLengthResponse(String.format(format, route.run(session.getParameters())));
            }
        }

        return NanoHTTPD.newFixedLengthResponse(String.format(format, "null"));
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
