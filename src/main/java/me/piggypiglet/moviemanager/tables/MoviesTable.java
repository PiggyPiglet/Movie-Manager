package me.piggypiglet.moviemanager.tables;

import co.aikar.idb.DbRow;
import me.piggypiglet.framework.mysql.components.row.objects.KeyValueSet;
import me.piggypiglet.framework.mysql.table.Table;
import me.piggypiglet.moviemanager.objects.Movie;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MoviesTable extends Table<Movie> {
    public MoviesTable() {
        super("data");
    }

    @Override
    protected Movie rowToType(DbRow dbRow) {
        return new Movie(dbRow.getString("title"), dbRow.getString("og_title"), dbRow.getString("img"), dbRow.getString("desc"), dbRow.getString("path"));
    }

    @Override
    protected KeyValueSet typeToRow(Movie movie) {
        return KeyValueSet.builder()
                .key("title").value(movie.getName())
                .key("og_title").value(movie.getOgTitle())
                .key("img").value(movie.getImg())
                .key("desc").value(movie.getDescription())
                .key("path").value(movie.getPath())
                .build();
    }
}
