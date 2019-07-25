package me.piggypiglet.moviemanager.managers;

import me.piggypiglet.moviemanager.utils.SearchUtils;

import java.util.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Manager<T extends SearchUtils.Searchable> {
    protected final Map<Integer, T> items = new HashMap<>();

    protected abstract Map<Integer, T> populate(List<String> data);

    public Collection<T> search(String query) {
        return SearchUtils.search(new ArrayList<>(items.values()), query);
    }

    public T get(int id) {
        return items.get(id);
    }

    public Map<Integer, T> getAll() {
        return items;
    }
}
