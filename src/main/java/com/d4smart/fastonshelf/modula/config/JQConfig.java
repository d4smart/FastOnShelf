package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JQConfig implements Config {

    /**
     * jq
     */
    private String jq;

    @Override
    public String getType() {
        return "jq";
    }
}
