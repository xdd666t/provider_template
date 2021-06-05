package live_templates;

import com.intellij.codeInsight.template.TemplateContextType;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class ProviderContext extends TemplateContextType {
    protected ProviderContext() {
        super("FLUTTER", "Flutter");
    }

    @Override
    public boolean isInContext(@NotNull PsiFile file, int offset) {
        return file.getName().endsWith(".dart");
    }
}
