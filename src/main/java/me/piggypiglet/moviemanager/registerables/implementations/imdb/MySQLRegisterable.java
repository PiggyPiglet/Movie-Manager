package me.piggypiglet.moviemanager.registerables.implementations.imdb;

import co.aikar.idb.DB;
import co.aikar.idb.DatabaseOptions;
import co.aikar.idb.PooledDatabaseOptions;
import com.google.inject.Inject;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.guice.annotations.file.SQL;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.task.Task;
import org.slf4j.LoggerFactory;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MySQLRegisterable extends Registerable {
    @Inject @Config private FileConfiguration config;
    @Inject @SQL private String sql;

    @Override
    protected void execute() {
        Task.async(r -> {
            final String[] tables = {"data"};
            final String[] schemas = sql.split("-");

            DatabaseOptions options = DatabaseOptions.builder().mysql(
                    config.getString("mysql.username"),
                    config.getString("mysql.password"),
                    config.getString("mysql.db"),
                    config.getString("mysql.host")
            ).build();

            DB.setGlobalDatabase(PooledDatabaseOptions.builder().options(options).createHikariDatabase());

            for (int i = 0; i < tables.length; i++) {
                try {
                    if (DB.getFirstRow("SHOW TABLES LIKE '" + tables[i] + "'") == null) {
                        DB.executeInsert(schemas[i++]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            LoggerFactory.getLogger("MySQL").info("Database successfully initialized.");
        });
    }
}
