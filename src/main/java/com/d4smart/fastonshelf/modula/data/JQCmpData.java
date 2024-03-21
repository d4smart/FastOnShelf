package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JQCmpData implements CmpData {

    /**
     * 结果数据
     */
    private Object data;


    @Override
    public String getType() {
        return "jq";
    }
}
