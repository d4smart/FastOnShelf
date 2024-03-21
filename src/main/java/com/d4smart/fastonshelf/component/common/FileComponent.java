package com.d4smart.fastonshelf.component.common;

import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.modula.config.FileConfig;
import com.d4smart.fastonshelf.modula.data.FileCmpData;
import com.fasterxml.jackson.databind.JsonNode;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

@Component("file")
public class FileComponent extends NodeComponent implements FlowHelper {

    @Override
    public void process() {
        // 获取组件配置
        FileConfig config = (FileConfig) ComponentContextHandler.getContext().getConfig();
        // 读取文件内容并执行jq
        String content = this.readFile(config.getPath());
        JsonNode data = this.evaluate(config.getJq(), content);
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new FileCmpData(config.getPath(), data));
    }
}
