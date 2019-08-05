package me.piggypiglet.moviemanager.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class StringUtils {
    public static boolean endsWith(String str, String... elements) {
        return lowercaseStream(Arrays.asList(elements)).anyMatch(str.toLowerCase()::endsWith);
    }

    private static Stream<String> lowercaseStream(List<String> elements) {
        return elements.stream().map(String::toLowerCase);
    }
}
