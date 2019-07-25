package me.piggypiglet.moviemanager.utils;

import co.aikar.idb.DB;
import co.aikar.idb.DbRow;
import com.google.common.base.Preconditions;
import me.piggypiglet.moviemanager.task.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MySQLUtils {
    public static CompletableFuture<Boolean> create(String table, String[] keys, Object... values) {
        Preconditions.checkArgument(keys.length == values.length, "Key length doesn't match value length.");

        CompletableFuture<Boolean> success = new CompletableFuture<>();
        String formattedKeys = keyFormat(keys);
        String formattedValues = valueFormat(values);

        Task.mysql(r -> {
            try {
                DB.executeInsert(format("INSERT INTO " + table + " " + formattedKeys + " VALUES " + formattedValues, values));
                success.complete(true);
            } catch (Exception e) {
                success.complete(false);
                e.printStackTrace();
            }
        });

        return success;
    }

    public static boolean set(String table, Map.Entry<String[], Object[]> location, Map.Entry<String[], Object[]> replace) {
        String[] replaceKeys = replace.getKey();
        Object[] replaceValues = replace.getValue();
        String[] locKeys = location.getKey();
        Object[] locValues = location.getValue();
        Preconditions.checkArgument(replaceKeys.length == replaceValues.length && locKeys.length == locValues.length, "Replace keys don't match value length, or Loc keys don't match value length");

        boolean success = false;
        int replaceKeysLength = replaceKeys.length;

        if (replaceKeysLength > 0) {
            StringBuilder replacements = new StringBuilder();

            for (int i = 0; i < replaceKeysLength - 2; ++i) {
                replacements.append(replaceKeys[i]).append("=").append("%s");
            }

            replacements.append(replaceKeys[replaceKeysLength - 1]).append("=").append("%s");

            try {
                if (exists(table, location.getKey(), location.getValue())) {
                    DB.executeUpdateAsync("UPDATE `" + table + "` SET " + format(replacements.toString(), replaceValues) + " WHERE " + whereFormat(locKeys, locValues) + ";");
                }
                success = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public static CompletableFuture<DbRow> getRow(String table, String[] keys, Object[] values) {
        return DB.getFirstRowAsync("SELECT * FROM `" + table + "` WHERE " + whereFormat(keys, values) + ";");
    }

    public static CompletableFuture<List<DbRow>> getRows(String table) {
        return DB.getResultsAsync("SELECT * FROM `" + table + "`;");
    }

    public static boolean remove(String table, String[] keys, Object[] values) {
        Preconditions.checkArgument(keys.length == values.length, "Key length doesn't match values length.");

        boolean success = false;

        try {
            if (exists(table, keys, values)) {
                DB.executeUpdateAsync("DELETE FROM `" + table + "` WHERE " + whereFormat(keys, values) + ";");
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return success;
    }

    public static boolean exists(String table, String[] keys, Object[] values) {
        Preconditions.checkArgument(keys.length == values.length, "Key and value length don't match.");
        boolean exists = false;

        try {
            exists = DB.getFirstRowAsync("SELECT * FROM `" + table + "` WHERE " + whereFormat(keys, values) + ";").get() != null;
        } catch (Exception ignored) {}

        return exists;
    }

    private static String keyFormat(String[] keys) {
        StringBuilder keysBuilder = new StringBuilder("(`id`");
        Arrays.stream(keys).forEach(k -> keysBuilder.append(", `").append(k).append("`"));
        keysBuilder.append(")");
        return keysBuilder.toString();
    }

    private static String valueFormat(Object[] values) {
        StringBuilder valuesBuilder = new StringBuilder("('0'");
        for (int i = 0; i < values.length; ++i) valuesBuilder.append(", ").append("%s");
        valuesBuilder.append(");");
        return valuesBuilder.toString();
    }

    private static String whereFormat(String[] keys, Object[] values) {
        return "`" + format(String.join("=%s AND `", keys) + "`=%s", values);
    }

    private static String format(String str, Object... variables) {
        final AtomicReference<String> string = new AtomicReference<>(str);

        if (variables != null) {
            Arrays.stream(variables).forEach(param -> {
                switch (param.getClass().getSimpleName()) {
                    case "String":
                        string.set(string.get().replaceFirst("%s", "'" + ((String) param).replaceAll("[^A-Za-z0-9.\\[\\]\\-/_:<>@\\s ]", "") + "'"));
                        break;

                    case "Integer":
                        string.set(string.get().replaceFirst("%s", Integer.toString((Integer) param)));
                        break;

                    case "Long":
                        string.set(string.get().replaceFirst("%s", Long.toString((Long) param)));
                        break;
                }
            });
        }

        return string.get();
    }
}
