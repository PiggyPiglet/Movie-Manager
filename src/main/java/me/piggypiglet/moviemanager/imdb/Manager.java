package me.piggypiglet.moviemanager.imdb;

import me.piggypiglet.moviemanager.utils.SearchUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Manager<T extends SearchUtils.Searchable> {
    private Map<Integer, T> items;

    protected abstract Map<Integer, T> populate(List<String> data);

    public void setup(List<String> folders) {
        items = populate(folders);
    }

    public List<T> search(String query) {
        return SearchUtils.search(new ArrayList<>(items.values()), query);
    }

    public T get(int id) {
        return items.get(id);
    }

    public Map<Integer, T> getAll() {
        return items;
    }
}
