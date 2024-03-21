package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartConfig implements Config {

    /**
     * 输入参数的jq
     */
    private String jq;

    @Override
    public String getType() {
        return "start";
    }
}
