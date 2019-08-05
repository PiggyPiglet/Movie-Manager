package me.piggypiglet.moviemanager.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.utils.FileUtils;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class ManagersManager {
    @Inject @Config private FileConfiguration config;

    private final List<Manager> managers = new ArrayList<>();

    public void process() {
        List<String> movies = Arrays.asList(FileUtils.getSubFiles(config.getString("movie-dir"), (f, n) -> new File(f, n).isDirectory()));
        managers.forEach(m -> m.setup(config.getString("movie-dir"), movies));
    }

    @SuppressWarnings("unchecked")
    public void save() {
        managers.forEach(m -> {
            List<?> items = m.getAll();
            items.forEach(m.getTable()::save);
            LoggerFactory.getLogger("Managers").info("Saved " + items.size() + " items from " + m.getClass().getSimpleName());
        });
    }

    public List<Manager> getManagers() {
        return managers;
    }
}
