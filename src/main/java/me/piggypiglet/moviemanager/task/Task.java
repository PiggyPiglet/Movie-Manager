package me.piggypiglet.moviemanager.task;

import sh.okx.timeapi.TimeAPI;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class Task {
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(13);
    private static final ExecutorService MYSQL_EXECUTOR = Executors.newFixedThreadPool(10);
    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10);

    public static void async(final Consumer<GRunnable> task) {
        EXECUTOR.submit(gRunnable(task));
    }

    public static void async(final Consumer<GRunnable> task, String time, boolean repeat) {
        long millis = new TimeAPI(time).getMilliseconds();

        if (repeat) {
            SCHEDULER.scheduleAtFixedRate(gRunnable(task), millis, millis, TimeUnit.MILLISECONDS);
        } else {
            SCHEDULER.schedule(gRunnable(task), millis, TimeUnit.MILLISECONDS);
        }
    }

    public static void mysql(final Consumer<GRunnable> task) {
        MYSQL_EXECUTOR.submit(gRunnable(task));
    }

    public static void shutdown() {
        EXECUTOR.shutdownNow();
        SCHEDULER.shutdownNow();
    }

    private static GRunnable gRunnable(final Consumer<GRunnable> consumer) {
        return new GRunnable() {
            @Override
            public void run() {
                consumer.accept(this);
            }
        };
    }
}