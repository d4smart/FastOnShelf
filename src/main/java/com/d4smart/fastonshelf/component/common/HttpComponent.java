package com.d4smart.fastonshelf.component.common;

import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.HttpConfig;
import com.d4smart.fastonshelf.modula.data.HttpCmpData;
import com.yomahub.liteflow.core.NodeComponent;
import okhttp3.*;
import okio.ByteString;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component("http")
public class HttpComponent extends NodeComponent implements FlowHelper {

    @Autowired
    private OkHttpClient okHttpClient;

    @Override
    public void process() throws IOException {
        // 获取通用context和组件配置
        WorkflowContext context = this.getContextBean(WorkflowContext.class);
        HttpConfig config = (HttpConfig) ComponentContextHandler.getContext().getConfig();
        // 执行jq，获取请求头和请求体
        Map<String, Object> header = this.evaluate(config.getHeaderJQ(), context.getDataString(), Map.class);
        Object requestData = null;
        if (StringUtils.isNotBlank(config.getRequestJQ())) {
            requestData = this.evaluate(config.getRequestJQ(), context.getDataString());
            if (this.isLoop()) {
                requestData = this.getLoopData(requestData);
            }
        }
        // 构建请求
        Request.Builder requestBuilder = new Request.Builder().url(config.getUrl());
        if (header != null) {
            header.forEach((key, value) -> requestBuilder.addHeader(key, value != null ? value.toString() : ""));
        }
        if ("get".equals(config.getMethod())) {
            requestBuilder.get();
        } else if ("post".equals(config.getMethod())) {
            requestBuilder.post(RequestBody.create(MediaType.get("application/json"), ByteString.encodeUtf8(JSON.toJSONString(requestData))));
        } else {
            throw new IllegalArgumentException("不支持的请求方法");
        }
        // 发送请求
        Response response = okHttpClient.newCall(requestBuilder.build()).execute();
        // 执行jq，获取responseData
        Object responseData = null;
        if (StringUtils.isNotBlank(config.getResponseJQ())) {
            responseData = this.evaluate(config.getResponseJQ(), response.body().string());
        }
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new HttpCmpData(config.getUrl(), config.getMethod(), header, requestData, responseData));
    }
}
