package helper;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

//custom save location
@com.intellij.openapi.components.State(
        name = "ProviderDataService",
        storages = {@Storage(value = "ProviderDataService.xml")
        })
public class DataService implements PersistentStateComponent<DataService> {
    //default true: use high mode
    public boolean defaultMode = ProviderConfig.defaultMode;

    //default true
    public boolean useFolder = ProviderConfig.useFolder;

    //default false
    public boolean usePrefix = ProviderConfig.usePrefix;

    //default false
    public boolean nullSafety = ProviderConfig.nullSafety;


    //Logical layer name
    public String logicName = ProviderConfig.logicName;

    //view layer name
    public String viewName = ProviderConfig.viewName;
    public String viewFileName = ProviderConfig.viewFileName;

    //state layer name
    public String stateName = ProviderConfig.stateName;

    public static DataService getInstance() {
        return ServiceManager.getService(DataService.class);
    }

    @Override
    public DataService getState() {
        return this;
    }

    @Override
    public void loadState(DataService state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}

