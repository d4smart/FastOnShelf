package com.d4smart.fastonshelf.modula.data;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DubboCmpData.class, name = "dubbo"),
        @JsonSubTypes.Type(value = EndCmpData.class, name = "end"),
        @JsonSubTypes.Type(value = FileCmpData.class, name = "file"),
        @JsonSubTypes.Type(value = HttpCmpData.class, name = "http"),
        @JsonSubTypes.Type(value = JQCmpData.class, name = "jq"),
        @JsonSubTypes.Type(value = MsgCmpData.class, name = "msg"),
        @JsonSubTypes.Type(value = RuleCmpData.class, name = "rule"),
        @JsonSubTypes.Type(value = ScriptCmpData.class, name = "script"),
        @JsonSubTypes.Type(value = StartCmpData.class, name = "start"),
})
public interface CmpData {

    String getType();
}
