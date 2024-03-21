package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MsgCmpData implements CmpData {

    /**
     * 消息主题
     */
    private String topic;

    /**
     * 消息tags
     */
    private Set<String> tags;

    /**
     * 消息体
     */
    private Object messageBody;


    @Override
    public String getType() {
        return "msg";
    }
}
