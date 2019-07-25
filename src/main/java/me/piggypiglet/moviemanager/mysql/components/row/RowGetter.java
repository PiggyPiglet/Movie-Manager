package me.piggypiglet.moviemanager.mysql.components.row;

import me.piggypiglet.moviemanager.mysql.components.MySQLComponent;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class RowGetter {
    static RowGetter.Builder builder(String table) {
        return new Builder(table);
    }

    public static class Builder extends MySQLComponent {
        private final String table;

        private Builder(String table) {
            this.table = table;
        }

        @Override
        public Object execute() {
            return null;
        }
    }
}
