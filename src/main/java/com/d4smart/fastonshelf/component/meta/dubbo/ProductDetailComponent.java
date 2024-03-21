package com.d4smart.fastonshelf.component.meta.dubbo;

import com.d4smart.fastonshelf.component.meta.DubboComponent;
import org.springframework.stereotype.Component;

@Component("product_detail")
public class ProductDetailComponent extends DubboComponent {

    /**
     * 获取门票资源详情
     * 数据是mock的
     */
    @Override
    protected Object doProcess(Object request) {
        return this.evaluate(".productDetail", this.readFile("mock/data01.json"));
    }
}
