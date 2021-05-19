package helper;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

//如果包名和类名和其他插件相同，会引起IDEA奔溃的坑比问题
//故此处类名起的稍微与众不同点
//custom save location
@com.intellij.openapi.components.State(
        name = "ProviderDataService",
        storages = {@Storage(value = "ProviderDataService.xml")
        })
public class ProviderTaoData implements PersistentStateComponent<ProviderTaoData> {
    // 0:default  1:high   2:extended
    public int defaultMode = ProviderConfig.defaultMode;

    //default true
    public boolean useFolder = ProviderConfig.useFolder;

    //default false
    public boolean usePrefix = ProviderConfig.usePrefix;


    //Logical layer name
    public String logicName = ProviderConfig.logicName;

    //view layer name
    public String viewName = ProviderConfig.viewName;
    public String viewFileName = ProviderConfig.viewFileName;

    //state layer name
    public String stateName = ProviderConfig.stateName;

    public static ProviderTaoData getInstance() {
        return ServiceManager.getService(ProviderTaoData.class);
    }

    @Override
    public ProviderTaoData getState() {
        return this;
    }

    @Override
    public void loadState(ProviderTaoData state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}

