package com.d4smart.fastonshelf.component.meta;

import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.DubboConfig;
import com.d4smart.fastonshelf.modula.data.DubboCmpData;
import com.yomahub.liteflow.core.NodeComponent;
import org.apache.commons.lang.StringUtils;

/**
 * dubbo组件
 * 抽象类，具体组件需要实现都Process方法
 */
public abstract class DubboComponent extends NodeComponent implements FlowHelper {

    @Override
    public void process() throws Exception {
        // 获取通用context和组件配置
        WorkflowContext context = this.getContextBean(WorkflowContext.class);
        DubboConfig config = (DubboConfig) ComponentContextHandler.getContext().getConfig();
        // 执行jq，获取请求数据
        Object requestData = null;
        if (StringUtils.isNotBlank(config.getRequestJQ())) {
            requestData = this.evaluate(config.getRequestJQ(), context.getDataString());
            if (this.isLoop()) {
                requestData = this.getLoopData(requestData);
            }
        }
        // 执行dubbo请求
        Object response = this.doProcess(requestData);
        // 执行jq，获取响应数据
        Object responseData = null;
        if (StringUtils.isNotBlank(config.getResponseJQ())) {
            responseData = this.evaluate(config.getResponseJQ(), JSON.toJSONString(response));
        }
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new DubboCmpData(requestData, responseData));
    }

    protected abstract Object doProcess(Object request) throws Exception;
}
