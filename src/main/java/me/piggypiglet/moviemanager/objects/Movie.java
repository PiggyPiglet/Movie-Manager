package me.piggypiglet.moviemanager.objects;

import me.piggypiglet.moviemanager.utils.SearchUtils;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Movie implements SearchUtils.Searchable {
    private final String title;
    private final String ogTitle;
    private final String img;
    private final String description;
    private final String path;

    public Movie(String title, String ogTitle, String img, String description, String path) {
        this.title = title;
        this.ogTitle = ogTitle;
        this.img = img;
        this.description = description;
        this.path = path;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOgTitle() {
        return ogTitle;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return String.format("Movie(title=%s, img=%s, description=%s)", title, img, description);
    }
}
