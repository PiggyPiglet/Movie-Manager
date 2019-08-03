package me.piggypiglet.moviemanager.registerables.implementations;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.file.FileManager;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.guice.annotations.file.SQL;
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
            addAnnotatedBinding(FileConfiguration.class, Config.class, fileManager.loadConfig("config", "/config.json", "./config.json"));
            addAnnotatedBinding(String.class, SQL.class, fileManager.loadFile("sql", "/schema.sql", null).getFileContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
