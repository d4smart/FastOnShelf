package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileConfig implements Config {

    /**
     * 文件路径
     */
    private String path;

    /**
     * 处理文件内容的jq
     */
    private String jq;

    @Override
    public String getType() {
        return "file";
    }
}
