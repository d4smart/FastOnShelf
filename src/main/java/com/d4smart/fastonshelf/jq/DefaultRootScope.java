package com.d4smart.fastonshelf.jq;

import net.thisptr.jackson.jq.BuiltinFunctionLoader;
import net.thisptr.jackson.jq.Scope;
import net.thisptr.jackson.jq.Version;
import net.thisptr.jackson.jq.Versions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultRootScope {

    private static final Map<Version, Scope> ROOT_SCOPES = new ConcurrentHashMap<>();

    public static Scope getInstance(final Version version) {
        return ROOT_SCOPES.computeIfAbsent(version, v -> {
            final Scope scope = Scope.newEmptyScope();
            BuiltinFunctionLoader.getInstance().loadFunctions(v, scope);
            return scope;
        });
    }

    public static Scope getScopeJQ1_6() {
        return getInstance(Versions.JQ_1_6);
    }
}
