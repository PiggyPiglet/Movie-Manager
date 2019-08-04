package me.piggypiglet.moviemanager.managers.implementations;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.managers.Manager;
import me.piggypiglet.moviemanager.mysql.implementations.MoviesTable;
import me.piggypiglet.moviemanager.objects.Movie;
import me.piggypiglet.moviemanager.utils.FileUtils;

import java.io.File;
import java.util.List;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class MovieManager extends Manager<Movie> {
    @Inject private TmdbApi tmdbApi;
    @Inject @Config private FileConfiguration config;

    protected MovieManager() {
        super(new MoviesTable());
    }

    @Override
    protected void populate(String parentPath, List<String> data) {
        TmdbSearch search = tmdbApi.getSearch();

        for (int i = 1; i < data.size() + 1; i++) {
            String datum = data.get(i - 1);

            if (i % 40 == 0) {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<MovieDb> foundMovies = search.searchMovie(datum, null, null, true, null).getResults();

            String title = datum;
            String img = "https://i.p1g.pw/poster.png";
            String desc = "null";
            String path = config.getString("public-url") + datum.replace(" ", "%20") + "/" + FileUtils.getSubFiles(parentPath + datum, (c, n) -> new File(c, n).getName().endsWith(".m4v"))[0];

            if (foundMovies.size() > 0) {
                MovieDb movieDb = foundMovies.get(0);
                title = movieDb.getTitle();
                img = movieDb.getPosterPath();
                desc = movieDb.getOverview();
            }

            items.add(new Movie(title, datum, img, desc, path));
        }
    }
}
