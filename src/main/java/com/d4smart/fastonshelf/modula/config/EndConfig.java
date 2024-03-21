package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndConfig implements Config {

    /**
     * 获取结果数据的jq
     */
    private String jq;

    @Override
    public String getType() {
        return "end";
    }
}
