package me.piggypiglet.moviemanager.imdb;

import me.piggypiglet.moviemanager.mysql.Table;
import me.piggypiglet.moviemanager.utils.SearchUtils;

import java.util.ArrayList;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Manager<S extends SearchUtils.Searchable> {
    private final Table<S> table;
    private List<S> items = new ArrayList<>();

    protected Manager(final Table<S> table) {
        this.table = table;
    }

    protected abstract List<S> populate(String parentPath, List<String> data);

    public void setup(String parentPath, List<String> folders) {
        table.getAll().whenComplete((s, t) -> {
            items.addAll(s);
            items.addAll(populate(parentPath, folders));
            System.out.println(items);
        });
    }

    @SuppressWarnings("unchecked")
    public List<S> search(String query) {
        return SearchUtils.search((List<SearchUtils.Searchable>) items, query);
    }

    public List<S> getAll() {
        return items;
    }
}
