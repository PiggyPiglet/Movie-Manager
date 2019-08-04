package me.piggypiglet.moviemanager.managers;

import com.google.inject.Singleton;
import me.piggypiglet.moviemanager.utils.FileUtils;

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
    private final List<Manager> managers = new ArrayList<>();

    public void process() {
        List<String> movies = Arrays.asList(FileUtils.getSubFiles("public/movies/", (f, n) -> new File(f, n).isDirectory()));
        managers.forEach(m -> m.setup("public/movies/", movies));
    }

    public void save() {
        managers.forEach(m -> m.getAll().forEach(m.getTable()::save));
    }

    public List<Manager> getManagers() {
        return managers;
    }
}
