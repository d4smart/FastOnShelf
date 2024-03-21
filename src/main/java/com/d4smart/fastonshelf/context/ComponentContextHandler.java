package com.d4smart.fastonshelf.context;

import com.alibaba.ttl.TransmittableThreadLocal;

public class ComponentContextHandler {

    private static final TransmittableThreadLocal<ComponentContext> contextThreadLocal = new TransmittableThreadLocal<>();

    /**
     * 获取组件执行上下文
     * @return 组件执行上下文
     */
    public static ComponentContext getContext() {
        ComponentContext context = contextThreadLocal.get();
        if (context == null) {
            context = new ComponentContext();
            contextThreadLocal.set(context);
        }
        return context;
    }

    /**
     * 清空组件执行上下文
     */
    public static void clear() {
        contextThreadLocal.remove();
    }
}
