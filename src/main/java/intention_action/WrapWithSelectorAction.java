package intention_action;

import org.jetbrains.annotations.NotNull;

public class WrapWithSelectorAction extends WrapWithAction {
    public WrapWithSelectorAction() {
        super(SnippetType.Selector);
    }

    @NotNull
    public String getText() {
        return "Wrap with Selector (selector)";
    }
}
