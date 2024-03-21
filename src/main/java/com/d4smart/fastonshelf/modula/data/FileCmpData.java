package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileCmpData implements CmpData {

    /**
     * 文件路径
     */
    private String path;

    /**
     * 执行结果
     */
    private Object data;


    @Override
    public String getType() {
        return "file";
    }
}
