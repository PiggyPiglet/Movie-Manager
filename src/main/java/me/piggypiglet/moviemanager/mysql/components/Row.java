package me.piggypiglet.moviemanager.mysql.components;

import java.util.concurrent.CompletableFuture;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Row extends MySQLComponent {
    private final String[] keys;
    private final Object[] values;

    private Row(String[] keys, Object[] values) {
        this.keys = keys;
        this.values = values;
    }

    public String[] getKeys() {
        return keys;
    }

    public Object[] getValues() {
        return values;
    }

    public static RowBuilder builder(String table) {
        return new RowBuilder(table);
    }

    public static class RowBuilder {
        private final String table;
        private String[] keys;
        private Object[] values;

        private RowBuilder(String table) {
            this.table = table;
        }

        public RowBuilder keys(String... keys) {
            this.keys = keys;
            return this;
        }

        public RowBuilder values(String... values) {
            this.values = values;
            return this;
        }

        public CompletableFuture<Boolean> execute() {
            Row row = new Row(keys, values);
            return row.create(table, row);
        }
    }
}
