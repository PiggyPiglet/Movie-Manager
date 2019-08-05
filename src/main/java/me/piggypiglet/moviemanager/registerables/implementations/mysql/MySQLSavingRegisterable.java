package me.piggypiglet.moviemanager.registerables.implementations.mysql;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.file.Config;
import me.piggypiglet.moviemanager.managers.ManagersManager;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.task.Task;
import org.slf4j.LoggerFactory;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MySQLSavingRegisterable extends Registerable {
    @Inject @Config private FileConfiguration config;
    @Inject private ManagersManager managersManager;

    @Override
    protected void execute() {
        Task.async(r -> managersManager.save(), config.getString("save-interval"), true);
    }
}
