package com.d4smart.fastonshelf;

import com.d4smart.fastonshelf.context.WorkflowContext;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.exception.LiteFlowException;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class WorkflowExecutor {

    private static final Logger logger = LoggerFactory.getLogger(WorkflowExecutor.class);

    @Resource
    private FlowExecutor flowExecutor;

    /**
     * 执行流程
     * @param chainName 流程名
     * @param params 输入参数
     * @param defineContext 自定义上下文
     * @return 执行结果
     */
    public Object execute(String chainName, Map<String, Object> params, Object... defineContext) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try (WorkflowContext workflowContext = new WorkflowContext(chainName)) {
            Object[] context = new Object[1];
            context[0] = workflowContext;
            if (defineContext != null && defineContext.length > 0) {
                // 自定义上下文不为空，则将自定义上下文追加到workflowContext后面
                context = new Object[defineContext.length + 1];
                context[0] = workflowContext;
                System.arraycopy(defineContext, 0, context, 1, defineContext.length);
            }

            LiteflowResponse response = flowExecutor.execute2Resp(chainName, params, context);
            if (!response.isSuccess()) {
                throw new LiteFlowException(response.getCause());
            }

            return workflowContext.getResultData();
        } catch (Exception e) {
            logger.error("liteflow execute failed", e);
            throw new LiteFlowException(e);
        } finally {
            stopWatch.stop();
            logger.info("liteflow chain [{}] execute time: {} ms", chainName, stopWatch.getTotalTimeMillis());
        }
    }
}
