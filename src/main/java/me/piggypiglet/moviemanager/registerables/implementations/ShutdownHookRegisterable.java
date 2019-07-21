package me.piggypiglet.moviemanager.registerables.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.ShutdownHook;
import me.piggypiglet.moviemanager.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ShutdownHookRegisterable extends Registerable {
    @Inject private ShutdownHook shutdownHook;

    @Override
    protected void execute() {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
}
