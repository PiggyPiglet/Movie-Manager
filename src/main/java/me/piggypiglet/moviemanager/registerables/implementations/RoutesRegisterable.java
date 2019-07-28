package me.piggypiglet.moviemanager.registerables.implementations;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.piggypiglet.moviemanager.http.responses.ResponseHandler;
import me.piggypiglet.moviemanager.http.routes.Route;
import me.piggypiglet.moviemanager.registerables.Registerable;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RoutesRegisterable extends Registerable {
    @Inject private Reflections reflections;
    @Inject private Injector injector;
    @Inject private ResponseHandler responseHandler;

    @Override
    protected void execute() {
        reflections.getSubTypesOf(Route.class).stream().map(injector::getInstance).forEach(responseHandler.getRoutes()::add);
    }
}
