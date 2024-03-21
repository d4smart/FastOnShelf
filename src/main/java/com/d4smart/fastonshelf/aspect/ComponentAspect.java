package com.d4smart.fastonshelf.aspect;

import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.ConfigHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.Config;
import com.d4smart.fastonshelf.utils.JQUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.yomahub.liteflow.aop.ICmpAroundAspect;
import com.yomahub.liteflow.core.NodeComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ComponentAspect implements ICmpAroundAspect, FlowHelper {

    private static final Logger logger = LoggerFactory.getLogger(ComponentAspect.class);

    private final Object lock = new Object();

    @Override
    public void beforeProcess(NodeComponent component) {
        // 开始计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // 读取配置
        JsonNode eval = JQUtils.eval("." + component.getTag(), ConfigHandler.getConfig(component.getChainId()));
        Config config = JSON.parseObject(eval.toString(), Config.class);
        // 设置组件执行context
        ComponentContextHandler.getContext().setConfig(config);
        ComponentContextHandler.getContext().setComponent(component);
        ComponentContextHandler.getContext().setStopWatch(stopWatch);
    }

    @Override
    public void afterProcess(NodeComponent component) {
        // 处理数据
        processResult(component);
        // 结束计时
        StopWatch stopWatch = ComponentContextHandler.getContext().getStopWatch();
        stopWatch.stop();
        logger.info("component {} execute {} ms", component.getTag(), stopWatch.getTotalTimeMillis());
        // 清空组件执行上下文
        ComponentContextHandler.clear();
    }

    private void processResult(NodeComponent component) {
        Object cmpData = ComponentContextHandler.getContext().getCmpData();
        if (cmpData == null) {
            return;
        }
        // 将cmpData放入通用context
        WorkflowContext context = component.getContextBean(WorkflowContext.class);
        if (this.isLoop()) {
            synchronized (lock) {
                // 循环，需要放入list
                Object data = context.getData(component.getTag());
                if (data == null) {
                    CopyOnWriteArrayList<Object> dataList = new CopyOnWriteArrayList<>();
                    dataList.add(cmpData);
                    context.setData(component.getTag(), dataList);
                } else {
                    List<Object> dataList = (List<Object>) data;
                    dataList.add(cmpData);
                }
            }
        } else {
            // 非循环，直接放
            context.setData(component.getTag(), cmpData);
        }
    }

    @Override
    public void onSuccess(NodeComponent component) {
        logger.info("liteflow component {} process success", component.getTag());
    }

    @Override
    public void onError(NodeComponent component, Exception e) {
        logger.error("liteflow component {} process fail, error: {}", component.getTag(), e.getMessage());
    }
}
