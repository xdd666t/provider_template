package intention_action;

import org.jetbrains.annotations.NotNull;

public class WrapWithSelectorShouldRebuildAction extends WrapWithAction {
    public WrapWithSelectorShouldRebuildAction() {
        super(SnippetType.SelectorShouldRebuild);
    }

    @NotNull
    public String getText() {
        return "Wrap with Selector (shouldRebuild)";
    }
}
