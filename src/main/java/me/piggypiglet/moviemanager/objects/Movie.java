package me.piggypiglet.moviemanager.objects;

import me.piggypiglet.moviemanager.utils.SearchUtils;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Movie implements SearchUtils.Searchable {
    private final String title;
    private final String img;
    private final String description;

    public Movie(String title, String img, String description) {
        this.title = title;
        this.img = img;
        this.description = description;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }
}
