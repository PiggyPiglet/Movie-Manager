package me.piggypiglet.moviemanager;

import com.google.inject.Injector;
import com.google.inject.Singleton;
import me.piggypiglet.moviemanager.guice.modules.BindingSetterModule;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.registerables.implementations.*;
import me.piggypiglet.moviemanager.registerables.implementations.imdb.ManagersRegisterable;
import me.piggypiglet.moviemanager.registerables.implementations.imdb.TMDBRegisterable;
import me.piggypiglet.moviemanager.registerables.implementations.mysql.MySQLRegisterable;
import me.piggypiglet.moviemanager.registerables.implementations.mysql.MySQLSavingRegisterable;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class MovieManager {
    void start(Injector parentInjector) {
        final AtomicReference<Injector> injector = new AtomicReference<>(parentInjector);

        Stream.of(
                FilesRegisterable.class,
                MySQLRegisterable.class,
                TMDBRegisterable.class,
                ManagersRegisterable.class,
                MySQLSavingRegisterable.class,
                RoutesRegisterable.class,
                HTTPRegisterable.class,
                ShutdownHookRegisterable.class,
                ConsoleRegisterable.class
        ).forEach(r -> {
            Registerable registerable = injector.get().getInstance(r);
            registerable.run(injector.get());

            if (registerable.getProviders().size() > 0 || registerable.getAnnotatedBindings().size() > 0 || registerable.getStaticInjections().size() > 0) {
                injector.set(injector.get().createChildInjector(new BindingSetterModule(
                        registerable.getProviders(),
                        registerable.getAnnotatedBindings(),
                        registerable.getStaticInjections().toArray(new Class[]{})
                )));
            }
        });

        LoggerFactory.getLogger("MovieManager").info("Startup completed.");
    }
}
