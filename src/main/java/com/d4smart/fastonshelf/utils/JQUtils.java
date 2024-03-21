package com.d4smart.fastonshelf.utils;

import com.d4smart.fastonshelf.jq.DefaultRootScope;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thisptr.jackson.jq.JsonQuery;
import net.thisptr.jackson.jq.Versions;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JQUtils {

    private static final Logger logger = LoggerFactory.getLogger(JQUtils.class);

    /**
     * 执行jq
     * @param expr jq表达式
     * @param body json数据
     * @return
     */
    public static JsonNode eval(String expr, String body) {
        Objects.requireNonNull(expr, "expr is null");
        Objects.requireNonNull(body, "body is null");
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(body);
            if (".".equals(expr.trim())) {
                // jq compile后默认会将.转换为.[]，因此这里需要特殊处理
                return jsonNode;
            }
            JsonQuery jq = JsonQuery.compile(expr, Versions.JQ_1_6);
            final List<JsonNode> results = new ArrayList<>();
            jq.apply(DefaultRootScope.getScopeJQ1_6(), jsonNode, results::add);
            if (CollectionUtils.isEmpty(results)) {
                return null;
            }
            if (results.size() == 1) {
                return results.get(0);
            }
            return mapper.createArrayNode().addAll(results);
        } catch (IOException e) {
            logger.error("jq eval error", e);
            throw new RuntimeException(e);
        }
    }
}
