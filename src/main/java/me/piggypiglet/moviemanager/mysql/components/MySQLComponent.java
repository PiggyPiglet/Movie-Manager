package me.piggypiglet.moviemanager.mysql.components;

import me.piggypiglet.moviemanager.utils.mysql.MySQLUtils;

import java.util.concurrent.CompletableFuture;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class MySQLComponent {
    protected CompletableFuture<Boolean> create(String table, Row row) {
        return MySQLUtils.create(table, row.getKeys(), row.getValues());
    }
}
