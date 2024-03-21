package com.d4smart.fastonshelf.component.common;

import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.EndConfig;
import com.d4smart.fastonshelf.modula.data.EndCmpData;
import com.yomahub.liteflow.core.NodeComponent;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component("end")
public class EndComponent extends NodeComponent implements FlowHelper {

    @Override
    public void process() {
        // 获取通用context和组件配置
        WorkflowContext context = this.getContextBean(WorkflowContext.class);
        EndConfig config = (EndConfig) ComponentContextHandler.getContext().getConfig();
        if (StringUtils.isNotBlank(config.getJq())) {
            // 执行jq，并设置组件执行结果
            Object result = this.evaluate(config.getJq(), context.getDataString());
            ComponentContextHandler.getContext().setCmpData(new EndCmpData(result));
        }
        this.setIsEnd(true);
    }
}
