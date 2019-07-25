package me.piggypiglet.moviemanager.utils;

import me.xdrop.fuzzywuzzy.FuzzySearch;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class SearchUtils {
    @SuppressWarnings("unchecked")
    public static <T extends Searchable> Collection<T> search(List<Searchable> items, String query) {
        return items.stream()
                .map(i -> new SearchPair(i, query))
                .sorted()
                .map(i -> (T) i.item)
                .collect(Collectors.toList());
    }

    private static final class SearchPair implements Comparable {
        private final Searchable item;
        private final String query;
        private int similarity;

        private SearchPair(Searchable item, String query) {
            this.item = item;
            this.query = query;
            this.similarity = FuzzySearch.weightedRatio(item.getTitle(), query);
        }

        @Override
        public int compareTo(@Nonnull Object o) {
            if (!(o instanceof Searchable)) return 0;

            return similarity - FuzzySearch.weightedRatio(((Searchable) o).getTitle(), query);
        }
    }

    public interface Searchable {
        String getTitle();
    }
}
