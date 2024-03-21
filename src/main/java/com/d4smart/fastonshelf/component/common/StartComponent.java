package com.d4smart.fastonshelf.component.common;

import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.modula.config.StartConfig;
import com.d4smart.fastonshelf.modula.data.StartCmpData;
import com.yomahub.liteflow.core.NodeComponent;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component("start")
public class StartComponent extends NodeComponent implements FlowHelper {

    @Override
    public void process() {
        // 获取组件配置
        StartConfig config = (StartConfig) ComponentContextHandler.getContext().getConfig();
        // 执行jq
        Object params = this.getRequestData();
        if (StringUtils.isNotBlank(config.getJq())) {
            params = this.evaluate(config.getJq(), JSON.toJSONString(this.getRequestData()));
        }
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new StartCmpData(params));
    }
}
