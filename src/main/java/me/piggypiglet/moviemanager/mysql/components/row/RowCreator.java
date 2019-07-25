package me.piggypiglet.moviemanager.mysql.components.row;

import me.piggypiglet.moviemanager.mysql.components.MySQLComponent;

import java.util.concurrent.CompletableFuture;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RowCreator extends MySQLComponent {
    private RowCreator() {}

    static Builder builder(String table) {
        return new Builder(table);
    }

    public static class Builder {
        private final String table;
        private String[] keys;
        private Object[] values;

        private Builder(String table) {
            this.table = table;
        }

        public Builder keys(String... keys) {
            this.keys = keys;
            return this;
        }

        public Builder values(String... values) {
            this.values = values;
            return this;
        }

        public CompletableFuture<Boolean> execute() {
            Row row = new Row(keys, values);
            return new RowCreator().create(table, row);
        }
    }
}
