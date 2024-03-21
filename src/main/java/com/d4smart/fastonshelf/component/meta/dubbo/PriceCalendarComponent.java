package com.d4smart.fastonshelf.component.meta.dubbo;

import com.d4smart.fastonshelf.component.meta.DubboComponent;
import org.springframework.stereotype.Component;

@Component("price_calendar")
public class PriceCalendarComponent extends DubboComponent {

    /**
     * 获取门票价格日历
     * 数据是mock的
     */
    @Override
    protected Object doProcess(Object request) {
        return this.evaluate(".priceCalendar", this.readFile("mock/data01.json"));
    }
}
