package me.piggypiglet.moviemanager.registerables.implementations.mysql;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.managers.ManagersManager;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.task.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MySQLSavingRegisterable extends Registerable {
    @Inject private ManagersManager managersManager;

    @Override
    protected void execute() {
        Task.async(r -> managersManager.save(), "15 mins", true);
    }
}
