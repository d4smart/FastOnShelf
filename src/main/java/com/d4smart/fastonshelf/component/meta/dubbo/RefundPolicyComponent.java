package com.d4smart.fastonshelf.component.meta.dubbo;

import com.d4smart.fastonshelf.component.meta.DubboComponent;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

@Component("refund_policy")
public class RefundPolicyComponent extends DubboComponent {

    /**
     * 获取门票取消政策
     * 数据是mock的
     */
    @Override
    protected Object doProcess(Object request) {
        JsonNode params = (JsonNode) request;
        String resourceId = params.get("resourceId").asText();
        return this.evaluate(".refundPolicy", this.readFile("mock/data01.json")).get(resourceId);
    }
}
