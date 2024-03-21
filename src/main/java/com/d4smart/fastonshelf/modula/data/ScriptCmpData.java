package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScriptCmpData implements CmpData {

    /**
     * 脚本执行结果
     */
    private Object data;


    @Override
    public String getType() {
        return "script";
    }
}
