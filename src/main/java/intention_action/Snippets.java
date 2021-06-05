package intention_action;

import helper.ProviderTaoData;

public class Snippets {
    public static final ProviderTaoData data = ProviderTaoData.getInstance();

    public static final String PREFIX_SELECTION = "Subject";
    public static final String SUFFIX1 = data.logicName;

    public static final String Provider_SNIPPET_KEY = PREFIX_SELECTION + SUFFIX1;


    static String getSnippet(SnippetType snippetType, String widget) {
        switch (snippetType) {
            case Consumer:
                return snippetConsumer(widget);
            case Selector:
                return snippetSelector(widget);
            case ChangeNotifierProvider:
                return snippetChangeNotifierProvider(widget);
            default:
                return "";
        }
    }

    private static String snippetConsumer(String widget) {
        return String.format("Consumer<%1$s>(\n" +
                "  builder: (context, provider, child) {\n" +
                "  return %3$s;\n" +
                "  },\n" +
                ")", Provider_SNIPPET_KEY, data.logicName.toLowerCase(), widget);
    }

    private static String snippetSelector(String widget) {
        return String.format("Selector<%1$s, %1$s>(\n" +
                "  shouldRebuild: (previous, next) {\n" +
                "    return true;\n" +
                "  },\n" +
                "  selector: (context, %2$s) => %2$s,\n" +
                "  builder: (context, %2$s, child) {\n" +
                "    return %3$s;\n" +
                "  },\n" +
                ")", Provider_SNIPPET_KEY, data.logicName.toLowerCase(), widget);
    }

    private static String snippetChangeNotifierProvider(String widget) {
        return String.format("ChangeNotifierProvider(\n" +
                "  create: (BuildContext context) => %1$s(),\n" +
                "  child: %2$s,\n" +
                ")", Provider_SNIPPET_KEY, widget);
    }
}
