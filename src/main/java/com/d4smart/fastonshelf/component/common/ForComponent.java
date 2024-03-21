package com.d4smart.fastonshelf.component.common;

import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.ScriptConfig;
import com.d4smart.fastonshelf.modula.data.ScriptCmpData;
import com.fasterxml.jackson.databind.JsonNode;
import com.yomahub.liteflow.core.NodeForComponent;
import org.springframework.stereotype.Component;

@Component("for_index")
public class ForComponent extends NodeForComponent implements FlowHelper {

    @Override
    public int processFor() {
        // 获取通用context和组件配置
        WorkflowContext context = this.getContextBean(WorkflowContext.class);
        ScriptConfig config = (ScriptConfig) ComponentContextHandler.getContext().getConfig();
        // 执行jq
        JsonNode data = this.evaluate(config.getJq(), context.getDataString());
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new ScriptCmpData(data));
        // 返回循环次数
        return data.size();
    }
}
