package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DubboConfig implements Config {

    /**
     * 请求参数的jq
     */
    private String requestJQ;

    /**
     * 响应数据的jq
     */
    private String responseJQ;

    @Override
    public String getType() {
        return "dubbo";
    }
}
