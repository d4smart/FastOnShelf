package com.d4smart.fastonshelf.component.common;

import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.JQConfig;
import com.d4smart.fastonshelf.modula.data.JQCmpData;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("jq")
public class JQComponent extends NodeComponent implements FlowHelper {

    @Override
    public void process() {
        // 获取通用context和组件配置
        WorkflowContext context = this.getContextBean(WorkflowContext.class);
        JQConfig config = (JQConfig) ComponentContextHandler.getContext().getConfig();
        // 执行jq
        Object data = this.evaluate(config.getJq(), context.getDataString());
        if (this.isLoop()) {
            data = this.getLoopData(data);
        }
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new JQCmpData(data));
    }
}
