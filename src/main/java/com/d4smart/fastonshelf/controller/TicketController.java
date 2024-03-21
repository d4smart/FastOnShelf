package com.d4smart.fastonshelf.controller;

import com.d4smart.fastonshelf.WorkflowExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private WorkflowExecutor workflowExecutor;

    @RequestMapping("/sync")
    public String sync(@RequestParam("gdsProductId") Long gdsProductId, @RequestParam(name = "concurrent", defaultValue = "false") boolean concurrent){
        Map<String, Object> params = new HashMap<>();
        params.put("gdsProductId", gdsProductId);
        String chainName = !concurrent ? "sync_ticket" : "sync_ticket_concurrent";
        Object response = workflowExecutor.execute(chainName, params);
        return response.toString();
    }
}
