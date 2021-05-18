package helper;

// set default value
public class ProviderConfig {
    //default true: use high mode
    public static boolean defaultMode = true;

    //default true
    public static boolean useFolder = true;

    //default false
    public static boolean usePrefix = false;

    //default false
    public static boolean nullSafety = false;


    //Logical layer name
    public static String logicName = "Provider";

    //view layer name
    public static String viewName = "Page";
    public static String viewFileName = "View";

    //state layer name
    public static String stateName = "State";

    //mode name
    public static final String modeDefault = "Default";
    public static final String modeHigh = "High";
    public static final String modeExtended = "Extended";
}
