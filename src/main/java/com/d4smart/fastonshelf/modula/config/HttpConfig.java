package com.d4smart.fastonshelf.modula.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpConfig implements Config {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求方法，get或post
     */
    private String method;

    /**
     * 请求头jq
     */
    private String headerJQ;

    /**
     * 请求内容jq
     */
    private String requestJQ;

    /**
     * 响应内容jq
     */
    private String responseJQ;

    @Override
    public String getType() {
        return "http";
    }
}
