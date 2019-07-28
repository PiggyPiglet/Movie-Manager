package me.piggypiglet.moviemanager.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
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
    private final Map<TypeLiteral, Class> typeLiterals;
    private final Class[] staticInjections;

    public BindingSetterModule(Map<Class, Object> providers, List<AnnotatedBinding> annotatedBindings, Map<TypeLiteral, Class> typeLiterals, Class... staticInjections) {
        this.providers = providers;
        this.annotatedBindings = annotatedBindings;
        this.typeLiterals = typeLiterals;
        this.staticInjections = staticInjections;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void configure() {
        providers.forEach((c, o) -> bind(c).toInstance(o));
        annotatedBindings.forEach(b -> bind(b.getClazz()).annotatedWith(b.getAnnotation()).toInstance(b.getInstance()));
        typeLiterals.forEach((t, c) -> bind(t).to(c));
        requestStaticInjection(staticInjections);
    }
}