package com.d4smart.fastonshelf.context;


import com.yomahub.liteflow.exception.LiteFlowException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigHandler {

    private static final Logger logger = LoggerFactory.getLogger(ConfigHandler.class);

    private static final ConcurrentHashMap<String, String> CONFIG_MAP = new ConcurrentHashMap<>();

    private static final Object lock = new Object();

    /**
     * 获取流程配置
     */
    public static String getConfig(String chainName) {
        if (!CONFIG_MAP.containsKey(chainName)) {
            loadConfig(chainName);
        }
        return CONFIG_MAP.get(chainName);
    }

    /**
     * 加载流程配置
     */
    public static void loadConfig(String chainName) {
        if (CONFIG_MAP.containsKey(chainName)) {
            return;
        }
        synchronized (lock) {
            if (CONFIG_MAP.containsKey(chainName)) {
                return;
            }
            String path = String.format("workflow/%s.json", chainName);
            try (InputStream inputStream = ConfigHandler.class.getClassLoader().getResourceAsStream(path)) {
                Objects.requireNonNull(inputStream);
                CONFIG_MAP.put(chainName, IOUtils.toString(inputStream));
            } catch (IOException e) {
                logger.error("load config failed", e);
                throw new LiteFlowException(String.format("load config failed, path is %s", path), e);
            }
        }
    }
}
