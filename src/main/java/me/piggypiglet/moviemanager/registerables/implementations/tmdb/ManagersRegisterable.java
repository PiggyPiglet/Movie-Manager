package me.piggypiglet.moviemanager.registerables.implementations.tmdb;

import com.google.inject.Inject;
import me.piggypiglet.moviemanager.managers.Manager;
import me.piggypiglet.moviemanager.managers.ManagersManager;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.task.Task;
import org.reflections.Reflections;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ManagersRegisterable extends Registerable {
    @Inject private Reflections reflections;
    @Inject private ManagersManager managersManager;

    @Override
    protected void execute() {
        reflections.getSubTypesOf(Manager.class).stream().map(injector::getInstance).forEach(managersManager.getManagers()::add);
        Task.async(r -> managersManager.process());
    }
}
