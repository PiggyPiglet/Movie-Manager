package me.piggypiglet.moviemanager.registerables.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.file.FileManager;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.Config;
import me.piggypiglet.moviemanager.registerables.Registerable;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class FilesRegisterable extends Registerable {
    @Inject private FileManager fileManager;

    @Override
    protected void execute() {
        try {
            addAnnotatedBinding(FileConfiguration.class, Config.class, fileManager.load("config", "/config.json", "./config.json"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
