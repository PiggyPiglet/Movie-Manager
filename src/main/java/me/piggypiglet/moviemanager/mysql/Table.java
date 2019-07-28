package me.piggypiglet.moviemanager.mysql;

import me.piggypiglet.moviemanager.mysql.components.row.RowCreator;
import me.piggypiglet.moviemanager.mysql.components.row.RowGetter;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Table {
    private final String table;

    protected Table(String table) {
        this.table = table;
    }

    protected RowCreator.Builder create() {
        return RowCreator.builder(table);
    }

    protected RowGetter.Builder getter() {
        return RowGetter.builder(table);
    }
}
