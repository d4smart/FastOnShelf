package com.d4smart.fastonshelf.modula.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DubboConfig.class, name = "dubbo"),
        @JsonSubTypes.Type(value = EndConfig.class, name = "end"),
        @JsonSubTypes.Type(value = FileConfig.class, name = "file"),
        @JsonSubTypes.Type(value = HttpConfig.class, name = "http"),
        @JsonSubTypes.Type(value = JQConfig.class, name = "jq"),
        @JsonSubTypes.Type(value = MsgConfig.class, name = "msg"),
        @JsonSubTypes.Type(value = RuleConfig.class, name = "rule"),
        @JsonSubTypes.Type(value = ScriptConfig.class, name = "script"),
        @JsonSubTypes.Type(value = StartConfig.class, name = "start")
})
public interface Config {

    String getType();
}
