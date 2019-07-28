package me.piggypiglet.moviemanager.mysql.components;

import co.aikar.idb.DbRow;
import me.piggypiglet.moviemanager.mysql.components.row.objects.Row;
import me.piggypiglet.moviemanager.utils.MySQLUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class MySQLComponent {
    protected CompletableFuture<Boolean> create(String table, Row row) {
        return MySQLUtils.create(table, row.getKeys(), row.getValues());
    }

    protected CompletableFuture<List<DbRow>> getAll(String table) {
        return MySQLUtils.getRows(table);
    }
}
