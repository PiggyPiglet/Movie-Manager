package me.piggypiglet.moviemanager.managers;

import me.piggypiglet.moviemanager.mysql.Table;
import me.piggypiglet.moviemanager.utils.SearchUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Manager<S extends SearchUtils.Searchable> {
    private final Table<S> table;

    protected final List<S> items = new ArrayList<>();

    protected Manager(final Table<S> table) {
        this.table = table;
    }

    protected abstract void populate(String parentPath, List<String> data);

    public void setup(String parentPath, List<String> folders) {
        table.getAll().whenComplete((s, t) -> {
            items.addAll(s);

            List<String> remaining = new ArrayList<>(folders);
            remaining.removeAll(items.stream().map(S::getOgTitle).collect(Collectors.toList()));

            populate(parentPath, remaining);
        });
    }

    @SuppressWarnings("unchecked")
    public List<S> search(String query) {
        return SearchUtils.search((List<SearchUtils.Searchable>) items, query);
    }

    public List<S> getAll() {
        return new ArrayList<>(items);
    }

    public Table<S> getTable() {
        return table;
    }
}
