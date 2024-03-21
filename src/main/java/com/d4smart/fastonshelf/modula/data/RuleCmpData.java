package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuleCmpData implements CmpData {

    /**
     * 规则文件名称
     */
    private String name;

    /**
     * 规则执行参数
     */
    private Object params;

    /**
     * 规则执行结果
     */
    private Object result;


    @Override
    public String getType() {
        return "rule";
    }
}
