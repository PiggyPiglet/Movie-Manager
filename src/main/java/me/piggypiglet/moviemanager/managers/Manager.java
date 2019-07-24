package me.piggypiglet.moviemanager.managers;

import java.util.HashMap;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Manager<T> {
    protected final Map<String, T> items = new HashMap<>();

    protected Manager(String route) {
        populate(route);
    }

    private void populate(String route) {
        //todo: populate items
    }

    protected abstract T search(String query);

    protected abstract T get(int id);

    public Map<String, T> getAll() {
        return items;
    }
}
