package me.piggypiglet.moviemanager.utils;

import java.io.File;
import java.io.FilenameFilter;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class FileUtils {
    // switch to java nio.2 alternative
    // https://stackoverflow.com/a/5125258/7204468
    public static String[] getSubFiles(String dir, FilenameFilter filter) {
        File file = new File(dir);

        if (file.exists()) {
            if (filter != null) {
                return file.list(filter);
            }

            return file.list();
        }

        return new String[0];
    }
}
