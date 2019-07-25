package me.piggypiglet.moviemanager.mysql;

import me.piggypiglet.moviemanager.mysql.components.row.Row;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Table {
    private final String table;

    protected Table(String table) {
        this.table = table;
    }

    protected Row.RowBuilder create() {
        return Row.builder(table);
    }
}
