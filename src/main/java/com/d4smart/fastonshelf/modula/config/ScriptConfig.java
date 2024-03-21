package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScriptConfig implements Config {

    /**
     * 脚本的jq
     */
    private String jq;

    @Override
    public String getType() {
        return "script";
    }
}
