package me.piggypiglet.moviemanager.registerables;

import me.piggypiglet.moviemanager.guice.objects.AnnotatedBinding;

import java.lang.annotation.Annotation;
import java.util.*;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
public abstract class Registerable {
    private final Map<Class, Object> providers = new HashMap<>();
    private final List<AnnotatedBinding> annotatedBindings = new ArrayList<>();
    private final List<Class> staticInjections = new ArrayList<>();

    protected abstract void execute();

    // TODO: provide injector back into registerable and inject members
    protected void addBinding(Class interfaze, Object instance) {
        providers.put(interfaze, instance);
    }

    protected void addBinding(Object instance) {
        providers.put(instance.getClass(), instance);
    }

    protected void addAnnotatedBinding(Class interfaze, Class<? extends Annotation> annotation, Object instance) {
        annotatedBindings.add(
                new AnnotatedBinding(interfaze, annotation, instance)
        );
    }

    protected void requestStaticInjections(Class... classes) {
        staticInjections.addAll(Arrays.asList(classes));
    }

    public void run() {
        execute();
    }

    public Map<Class, Object> getProviders() {
        return providers;
    }

    public List<AnnotatedBinding> getAnnotatedBindings() {
        return annotatedBindings;
    }

    public List<Class> getStaticInjections() {
        return staticInjections;
    }
}