package com.d4smart.fastonshelf;

import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.utils.JQUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.exception.LiteFlowException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface FlowHelper {

    /**
     * 执行jq，并转换成特定类型
     */
    default <T> T evaluate(String expr, String body, Class<T> clazz) {
        JsonNode jsonNode = evaluate(expr, body);
        if (jsonNode == null) {
            return null;
        }
        return JSON.parseObject(jsonNode.toString(), clazz);
    }

    /**
     * 执行jq
     */
    default JsonNode evaluate(String expr, String body) {
        try {
            return JQUtils.eval(expr, body);
        } catch (Exception e) {
            NodeComponent component = ComponentContextHandler.getContext().getComponent();
            String errorMsg = String.format("jq eval error\nchainName: %s\nnodeName: %s\nexpr: %s\nbody: %s",
                    component.getChainId(), component.getDisplayName().concat(" - ").concat(component.getTag()), expr, body);
            throw new LiteFlowException(errorMsg, e);
        }
    }

    /**
     * 读取文件内容
     * @param path 文件路径
     * @return 文件内容
     */
    default String readFile(String path) {
        try {
            return FileUtils.readFileToString(Objects.requireNonNull(FileUtils.toFile(this.getClass().getClassLoader().getResource(path))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 是否是循环
     */
    default boolean isLoop() {
        return ComponentContextHandler.getContext().getComponent().getLoopIndex() != null;
    }

    /**
     * 获取循环数据
     */
    default Object getLoopData(Object data) {
        if (data instanceof List) {
            List list = (List) data;
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(ComponentContextHandler.getContext().getComponent().getLoopIndex());
            }
        } else if (data instanceof JsonNode) {
            JsonNode jsonNode = (JsonNode) data;
            if (jsonNode.isArray()) {
                return jsonNode.get(ComponentContextHandler.getContext().getComponent().getLoopIndex());
            }
        } else if (data instanceof Object[]) {
            Object[] objects = (Object[]) data;
            if (objects.length > ComponentContextHandler.getContext().getComponent().getLoopIndex()) {
                return objects[ComponentContextHandler.getContext().getComponent().getLoopIndex()];
            }
        }
        return data;
    }
}
