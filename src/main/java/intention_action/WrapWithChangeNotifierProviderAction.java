package intention_action;

import org.jetbrains.annotations.NotNull;

public class WrapWithChangeNotifierProviderAction extends WrapWithAction {
    public WrapWithChangeNotifierProviderAction() {
        super(SnippetType.ChangeNotifierProvider);
    }

    @NotNull
    public String getText() {
        return "Wrap with ChangeNotifierProvider";
    }
}
