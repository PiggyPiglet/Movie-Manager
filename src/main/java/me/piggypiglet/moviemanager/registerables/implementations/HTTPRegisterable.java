package me.piggypiglet.moviemanager.registerables.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.http.HTTPServer;
import me.piggypiglet.moviemanager.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class HTTPRegisterable extends Registerable {
    @Inject private HTTPServer httpServer;

    @Override
    protected void execute() {
        httpServer.start();
    }
}
