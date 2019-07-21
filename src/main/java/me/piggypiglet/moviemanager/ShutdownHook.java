package me.piggypiglet.moviemanager;

import co.aikar.idb.DB;
import com.google.inject.Inject;
import me.piggypiglet.moviemanager.http.HTTPServer;
import me.piggypiglet.moviemanager.task.Task;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class ShutdownHook extends Thread {
    @Inject private HTTPServer httpServer;

    @Override
    public void run() {
        Task.shutdown();
        httpServer.stop();
        DB.close();
    }
}
