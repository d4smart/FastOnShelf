package com.d4smart.fastonshelf.component.common;

import com.alibaba.fastjson2.JSON;
import com.d4smart.fastonshelf.FlowHelper;
import com.d4smart.fastonshelf.context.ComponentContextHandler;
import com.d4smart.fastonshelf.context.WorkflowContext;
import com.d4smart.fastonshelf.modula.config.MsgConfig;
import com.d4smart.fastonshelf.modula.data.MsgCmpData;
import com.yomahub.liteflow.core.NodeComponent;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qunar.tc.qmq.Message;
import qunar.tc.qmq.MessageProducer;

/**
 * 消息组件，以qmq为例
 */
@Component("msg")
public class MsgComponent extends NodeComponent implements FlowHelper {

    @Autowired
    private MessageProducer messageProducer;


    @Override
    public void process() {
        // 获取通用context和组件配置
        WorkflowContext context = this.getContextBean(WorkflowContext.class);
        MsgConfig config = (MsgConfig) ComponentContextHandler.getContext().getConfig();
        // 执行jq，获取消息体
        Object messageBody = this.evaluate(config.getMessageBodyJQ(), context.getDataString());
        if (this.isLoop()) {
            messageBody = this.getLoopData(messageBody);
        }
        // 构建消息
        Message message = messageProducer.generateMessage(config.getTopic());
        message.setProperty("MessageBody", JSON.toJSONString(messageBody));
        if (CollectionUtils.isNotEmpty(config.getTags())) {
            config.getTags().forEach(message::addTag);
        }
        // 发送消息
        messageProducer.sendMessage(message);
        // 设置组件执行结果
        ComponentContextHandler.getContext().setCmpData(new MsgCmpData(config.getTopic(), config.getTags(), messageBody));
    }
}
