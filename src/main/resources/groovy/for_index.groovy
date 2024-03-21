package groovy

import com.alibaba.fastjson2.JSON
import com.d4smart.fastonshelf.context.ComponentContextHandler
import com.d4smart.fastonshelf.modula.config.ScriptConfig
import com.d4smart.fastonshelf.modula.data.ScriptCmpData
import com.d4smart.fastonshelf.utils.JQUtils

ScriptConfig scriptConfig = ComponentContextHandler.getContext().getConfig() as ScriptConfig

def data = JSON.parseObject(JQUtils.eval(scriptConfig.getJq(), workflowContext.getDataString()) as String, List.class)

ComponentContextHandler.getContext().setCmpData(new ScriptCmpData(data))

return data.size()
