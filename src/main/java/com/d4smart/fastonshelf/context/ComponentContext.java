package com.d4smart.fastonshelf.context;

import com.d4smart.fastonshelf.modula.config.Config;
import com.d4smart.fastonshelf.modula.data.CmpData;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StopWatch;

/**
 * 组件执行一次性上下文
 */
@Getter
@Setter
public class ComponentContext implements Cloneable {

    /**
     * 组件配置
     */
    private Config config;

    /**
     * 组件执行结果
     */
    private CmpData cmpData;

    /**
     * 组件自身的引用
     */
    private NodeComponent component;

    /**
     * 计时器
     */
    private StopWatch stopWatch;


    @Override
    public ComponentContext clone() {
        try {
            return (ComponentContext) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
