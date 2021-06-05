package intention_action;

import org.jetbrains.annotations.NotNull;

public class WrapWithConsumerAction extends WrapWithAction {
    public WrapWithConsumerAction() {
        super(SnippetType.Consumer);
    }

    @NotNull
    public String getText() {
        return "Wrap with Consumer";
    }
}
