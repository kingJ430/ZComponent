package com.common.core.base.injector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.AndroidInjector;
import dagger.internal.Beta;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * user: zhangjianfeng
 * date: 13/10/2017
 * version: 7.3
 */

@Beta
public final class DispatchingCoreAndroidInjector<T> implements AndroidInjector<T> {
    private static final String NO_SUPERTYPES_BOUND_FORMAT =
            "No injector factory bound for Class<%s>";
    private static final String SUPERTYPES_BOUND_FORMAT =
            "No injector factory bound for Class<%1$s>. Injector factories were bound for supertypes "
                    + "of %1$s: %2$s. Did you mean to bind an injector factory for the subtype?";

    private LinkedHashMap<Class<? extends T>, Provider<Factory<? extends T>>>
            injectorFactories;

    @Inject
    DispatchingCoreAndroidInjector(
            Map<Class<? extends T>, Provider<Factory<? extends T>>> injectorFactories) {
        if(this.injectorFactories == null) {
            this.injectorFactories = new LinkedHashMap<>();
        }
        this.injectorFactories.putAll(injectorFactories);

    }

    public void addAllMap(Map<Class<? extends T>, Provider<Factory<? extends T>>> provider) {
        if(injectorFactories != null) {
            injectorFactories.putAll(provider);
        }
    }

    public Map<Class<? extends T>, Provider<Factory<? extends T>>> getInjectorFactories() {
        return injectorFactories;
    }

    /**
     * Attempts to perform members-injection on {@code instance}, returning {@code true} if
     * successful, {@code false} otherwise.
     *
     * @throws dagger.android.DispatchingAndroidInjector.InvalidInjectorBindingException if the injector factory bound for a class does not
     *                                                                                   inject instances of that class
     */
//    @CanIgnoreReturnValue
    public boolean maybeInject(T instance) {
        Provider<Factory<? extends T>> factoryProvider =
                injectorFactories.get(instance.getClass());
        if (factoryProvider == null) {
            return false;
        }

        @SuppressWarnings("unchecked")
        AndroidInjector.Factory<T> factory = (AndroidInjector.Factory<T>) factoryProvider.get();
        try {
            AndroidInjector<T> injector =
                    checkNotNull(
                            factory.create(instance),
                            "%s.create(I) should not return null.",
                            factory.getClass().getCanonicalName());

            injector.inject(instance);
            return true;
        } catch (ClassCastException e) {
            throw new RuntimeException(
                    String.format(
                            "%s does not implement AndroidInjector.Factory<%s>",
                            factory.getClass().getCanonicalName(), instance.getClass().getCanonicalName()),
                    e);
        }
    }

    /**
     * Performs members-injection on {@code instance}.
     *
     * @throws dagger.android.DispatchingAndroidInjector.InvalidInjectorBindingException if the injector factory bound for a class does not
     *                                                                                   inject instances of that class
     * @throws IllegalArgumentException                                                  if no {@link AndroidInjector.Factory} is bound for {@code
     *                                                                                   instance}
     */
    @Override
    public void inject(T instance) {
        boolean wasInjected = maybeInject(instance);
        if (!wasInjected) {
            throw new IllegalArgumentException(errorMessageSuggestions(instance));
        }
    }

    /**
     * Exception thrown if an incorrect binding is made for a {@link AndroidInjector.Factory}. If you
     * see this exception, make sure the value in your {@code @ActivityKey(YourActivity.class)} or
     * {@code @FragmentKey(YourFragment.class)} matches the type argument of the injector factory.
     */
    @Beta
    public static final class InvalidInjectorBindingException extends RuntimeException {
        InvalidInjectorBindingException(String message, ClassCastException cause) {
            super(message, cause);
        }
    }

    /**
     * Returns an error message with the class names that are supertypes of {@code instance}.
     */
    private String errorMessageSuggestions(T instance) {
        List<String> suggestions = new ArrayList<String>();
        for (Class<? extends T> activityClass : injectorFactories.keySet()) {
            if (activityClass.isInstance(instance)) {
                suggestions.add(activityClass.getCanonicalName());
            }
        }
        Collections.sort(suggestions);

        return String.format(
                suggestions.isEmpty() ? NO_SUPERTYPES_BOUND_FORMAT : SUPERTYPES_BOUND_FORMAT,
                instance.getClass().getCanonicalName(),
                suggestions);
    }
}