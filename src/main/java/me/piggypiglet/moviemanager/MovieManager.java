package me.piggypiglet.moviemanager;

import com.google.inject.Injector;
import me.piggypiglet.moviemanager.guice.modules.BindingSetterModule;
import me.piggypiglet.moviemanager.registerables.Registerable;
import me.piggypiglet.moviemanager.registerables.implementations.ConsoleRegisterable;
import me.piggypiglet.moviemanager.registerables.implementations.FilesRegisterable;
import me.piggypiglet.moviemanager.registerables.implementations.HTTPRegisterable;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class MovieManager {
    void start(Injector parentInjector) {
        AtomicReference<Injector> injector = new AtomicReference<>(parentInjector);

        Stream.of(
                FilesRegisterable.class,
                HTTPRegisterable.class,
                ConsoleRegisterable.class
        ).forEach(r -> {
            Registerable registerable = injector.get().getInstance(r);
            registerable.run();

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
