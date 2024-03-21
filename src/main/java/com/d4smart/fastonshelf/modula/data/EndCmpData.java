package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EndCmpData implements CmpData {

    /**
     * 结果数据
     */
    private Object result;

    @Override
    public String getType() {
        return "end";
    }
}
