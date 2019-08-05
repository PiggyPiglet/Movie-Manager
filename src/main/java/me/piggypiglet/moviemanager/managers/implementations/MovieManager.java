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
import me.piggypiglet.moviemanager.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger("MovieManager");

    protected MovieManager() {
        super(new MoviesTable());
    }

    @Override
    protected void populate(String parentPath, List<String> data) {
        int dataSize = data.size();

        LOGGER.info("Importing {} movies, this could take a while depending on your internet speed. Estimated time is {} seconds.", dataSize, (dataSize / 40) * 4 + (dataSize / 2));
        TmdbSearch search = tmdbApi.getSearch();

        for (int i = 1; i < dataSize + 1; i++) {
            String datum = data.get(i - 1);
            System.out.println(datum);

            if (i % 100 == 0) {
                LOGGER.info("{} imported.", i);
            }

            if (i % 40 == 0) {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<MovieDb> foundMovies = search.searchMovie(datum.replace("_", ""), null, null, true, null).getResults();

            String title = datum.replace("_", "");
            String img = "https://i.p1g.pw/poster.png";
            String desc = "null";
            String path = config.getString("public-url") +
                    datum.replace(" ", "%20") + "/" +
                    FileUtils.getSubFiles(parentPath + datum, (c, n) -> StringUtils.endsWith(new File(c, n).getName(), ".m4v", ".mp4"))[0];

            if (foundMovies.size() > 0) {
                MovieDb movieDb = foundMovies.get(0);
                title = movieDb.getTitle();
                img = movieDb.getPosterPath();
                desc = movieDb.getOverview();
            }

            items.add(new Movie(title, datum, img, desc, path));
        }

        LOGGER.info("All movies imported.");
    }
}
