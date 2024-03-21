package groovy

import com.alibaba.fastjson2.JSON
import com.d4smart.fastonshelf.context.ComponentContextHandler
import com.d4smart.fastonshelf.modula.config.ScriptConfig
import com.d4smart.fastonshelf.modula.data.ScriptCmpData
import com.d4smart.fastonshelf.utils.JQUtils
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

ScriptConfig config = ComponentContextHandler.getContext().getConfig() as ScriptConfig
def data = JQUtils.eval(config.getJq(), workflowContext.getDataString())

Map<Integer, Integer> idMap = new HashMap<>()
for (int i = 0; i < data.get("product_detail").get("response").size(); i++) {
    // 保存门票信息
    def product = data.get("product_detail").get("response").get(i) as ObjectNode
    product.put("need_ticket", data.get("tags").get(i).get("response").get("need_ticket"))
    def addInfos = product.remove("add_infos") as List<ObjectNode>
    def productId = saveProduct(product)
    // 保存门票附加信息
    for (def addInfo : addInfos) {
        addInfo.put("product_id", productId)
    }
    saveAddInfos(addInfos)
    // 保存门票取消政策
    def refundPolicy = data.get("refund_policy").get(i).get("response") as ObjectNode
    def refundPolicyCosts = refundPolicy.remove("refund_policy_costs") as List<ObjectNode>
    refundPolicy.put("product_id", productId)
    def policyId = saveRefundPolicy(refundPolicy)
    // 保存门票取消政策扣款规则
    for (def refundPolicyCost : refundPolicyCosts) {
        refundPolicyCost.put("policy_id", policyId)
    }
    saveRefundPolicyCosts(refundPolicyCosts)
    // 保存resourceId和productId的对应关系，给门票价格日历用
    idMap.put(product.get("direct_resource_id").asInt(), productId)
}
for (int i = 0; i < data.get("price_calendar").get("response").size(); i++) {
    // 保存门票价格日历
    def priceCalendars = data.get("price_calendar").get("response").get(i) as List<ObjectNode>
    for (def priceCalendar : priceCalendars) {
        priceCalendar.put("product_id", idMap.get(priceCalendar.get("resource_id").asInt()))
    }
    savePriceCalendars(priceCalendars)
}

def saveProduct(JsonNode product) {
    def productId = new Random().nextInt(Integer.MAX_VALUE)
    println "save product:\n" + JSON.toJSONString(product) + "\nreturn: " + productId
    return productId
}

def saveAddInfos(List<JsonNode> addInfos) {
    println "save add_infos:\n" + JSON.toJSONString(addInfos)
}

def savePriceCalendars(List<JsonNode> priceCalendars) {
    println "save price_calendars:\n" + JSON.toJSONString(priceCalendars)
}

def saveRefundPolicy(JsonNode refundPolicy) {
    def policyId = new Random().nextInt(Integer.MAX_VALUE)
    println "save refund_policy:\n" + JSON.toJSONString(refundPolicy) + "\nreturn: " + policyId
    return policyId
}

def saveRefundPolicyCosts(List<JsonNode> refundPolicyCosts) {
    println "save refund_policy_costs:\n" + JSON.toJSONString(refundPolicyCosts)
}

ComponentContextHandler.getContext().setCmpData(new ScriptCmpData())