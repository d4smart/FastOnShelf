package com.d4smart.fastonshelf.context;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.modula.data.EndCmpData;
import com.yomahub.liteflow.exception.NullParamException;
import org.apache.commons.lang.StringUtils;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 流程执行通用上下文
 * 基于默认上下文{@link com.yomahub.liteflow.slot.DefaultContext}做了一些修改
 */
public class WorkflowContext implements Closeable  {

    /**
     * 所有线程公有
     */
    private final ConcurrentHashMap<String, Object> dataMap = new ConcurrentHashMap<>();

    public WorkflowContext(String chainName) {
        if (StringUtils.isBlank(chainName)) {
            throw new RuntimeException("chainName is null");
        }
        ConfigHandler.loadConfig(chainName);
    }

    private <T> void putDataMap(String key, T t) {
        if (ObjectUtil.isNull(t)) {
            throw new NullParamException("data can't accept null param");
        }
        dataMap.put(key, t);
    }

    /**
     * 通用上下文是否有对应key的数据
     */
    public boolean hasData(String key) {
        return dataMap.containsKey(key);
    }

    /**
     * 从通用上下文获取数据
     */
    public <T> T getData(String key) {
        return (T) dataMap.get(key);
    }

    /**
     * 通用上下文设置数据
     */
    public <T> void setData(String key, T t) {
        putDataMap(key, t);
    }

    /**
     * 获取string格式的通用上下文
     */
    public String getDataString() {
        Map<String, Object> copyMap = new HashMap<>(dataMap);
        return JSON.toJSONString(copyMap);
    }

    /**
     * 从通用上下文获取结果数据
     */
    public <T> T getResultData() {
        EndCmpData endCmpData = (EndCmpData) dataMap.get("end");
        return (T) endCmpData.getResult();
    }

    @Override
    public void close() {
        dataMap.clear();
    }
}
