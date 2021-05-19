package helper;

// set default value
public class ProviderConfig {
    // use high mode
    // 0:default  1:high
    public static int defaultMode = 0;

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
}
