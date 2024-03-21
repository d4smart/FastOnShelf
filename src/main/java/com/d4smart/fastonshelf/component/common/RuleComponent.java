package com.d4smart.fastonshelf.component.common;

import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.RuleConfig;
import com.d4smart.fastonshelf.modula.data.RuleCmpData;
import com.fasterxml.jackson.databind.JsonNode;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("rule")
public class RuleComponent extends NodeComponent implements FlowHelper {

    @Override
    public void process() {
//        WorkflowContext context = this.getContextBean(WorkflowContext.class);
//        RuleConfig config = (RuleConfig) ComponentContextHandler.getContext().getConfig();
//
//        JsonNode params = this.evaluate(config.getJq(), context.getDataString());
//        Object result = AviatorRuleHandler.handle(config.getName(), params);
//
//        ComponentContextHandler.getContext().setCmpData(new RuleCmpData(config.getName(), params, result));
    }
}
