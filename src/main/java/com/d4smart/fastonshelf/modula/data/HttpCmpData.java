package com.d4smart.fastonshelf.modula.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HttpCmpData implements CmpData {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求头
     */
    private Object header;

    /**
     * 请求体
     */
    private Object request;

    /**
     * 响应数据
     */
    private Object response;


    @Override
    public String getType() {
        return "http";
    }
}
