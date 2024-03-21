package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MsgConfig implements Config {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息tags
     */
    private Set<String> tags = new HashSet<>();

    /**
     * 消息体jq
     */
    private String messageBodyJQ;

    @Override
    public String getType() {
        return "msg";
    }
}
