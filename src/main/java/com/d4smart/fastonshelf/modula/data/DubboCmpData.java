package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DubboCmpData implements CmpData {

    /**
     * 请求参数
     */
    private Object request;

    /**
     * 响应数据
     */
    private Object response;

    @Override
    public String getType() {
        return "dubbo";
    }
}
