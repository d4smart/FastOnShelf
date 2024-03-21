package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RuleConfig implements Config {

    /**
     * 规则参数的jq
     */
    private String jq;

    /**
     * 规则文件名称
     */
    private String name;

    @Override
    public String getType() {
        return "rule";
    }
}
