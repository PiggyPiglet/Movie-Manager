package me.piggypiglet.moviemanager.guice.modules;

import com.google.inject.AbstractModule;
import me.piggypiglet.moviemanager.guice.objects.AnnotatedBinding;

import java.util.List;
import java.util.Map;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public final class BindingSetterModule extends AbstractModule {
    private final Map<Class, Object> providers;
    private final List<AnnotatedBinding> annotatedBindings;
    private final Class[] staticInjections;

    public BindingSetterModule(Map<Class, Object> providers, List<AnnotatedBinding> annotatedBindings, Class... staticInjections) {
        this.providers = providers;
        this.annotatedBindings = annotatedBindings;
        this.staticInjections = staticInjections;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure() {
        providers.forEach((c, o) -> bind(c).toInstance(o));
        annotatedBindings.forEach(b -> bind(b.getClazz()).annotatedWith(b.getAnnotation()).toInstance(b.getInstance()));
        requestStaticInjection(staticInjections);
    }
}