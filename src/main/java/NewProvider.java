import com.google.common.base.CaseFormat;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.JBColor;
import helper.ProviderData;
import helper.ProviderConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;


public class NewProvider extends AnAction {
    private Project project;
    private String psiPath;
    private ProviderData data;

    /**
     * Overall popup entity
     */
    private JDialog jDialog;
    private JTextField nameTextField;
    private ButtonGroup templateGroup;
    /**
     * Checkbox
     * Use folder：default true
     * Use prefix：default false
     */
    private JCheckBox folderBox, prefixBox, nullSafetyBox;

    @Override
    public void actionPerformed(AnActionEvent event) {
        project = event.getProject();
        psiPath = event.getData(PlatformDataKeys.PSI_ELEMENT).toString();
        psiPath = psiPath.substring(psiPath.indexOf(":") + 1);
        initData();
        initView();
    }

    private void initData() {
        data = ProviderData.getInstance();
        jDialog = new JDialog(new JFrame(), "Provider Template Code Produce");
    }

    private void initView() {
        //Set function button
        Container container = jDialog.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        //Set the main module style: mode, function
        //deal default value
        setMode(container);

        //Setting options: whether to use prefix
        //deal default value
        setCodeFile(container);

        //Generate file name and OK cancel button
        setNameAndConfirm(container);

        //Choose a pop-up style
        setJDialog();
    }

    /**
     * generate  file
     */
    private void save() {
        if (nameTextField.getText() == null || "".equals(nameTextField.getText().trim())) {
            Messages.showInfoMessage(project, "Please input the module name", "Info");
            return;
        }
        dispose();
        //Create a file
        createFile();
        //Refresh project
        project.getBaseDir().refresh(false, true);
    }

    /**
     * Set the overall pop-up style
     */
    private void setJDialog() {
        //The focus is on the current pop-up window,
        // and the focus will not shift even if you click on other areas
        jDialog.setModal(true);
        //Set padding
        ((JPanel) jDialog.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jDialog.setSize(400, 375);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);
    }

    /**
     * Main module
     */
    private void setMode(Container container) {
        //Two rows and two columns
        JPanel template = new JPanel();
        template.setLayout(new GridLayout(2, 2));
        //Set the main module style：mode, function
        template.setBorder(BorderFactory.createTitledBorder("Select Mode"));
        //default: high setting
        JRadioButton defaultBtn = new JRadioButton(ProviderConfig.modeDefault, data.defaultMode == 0);
        defaultBtn.setActionCommand(ProviderConfig.modeDefault);
        setPadding(defaultBtn, 5, 10);
        JRadioButton highBtn = new JRadioButton(ProviderConfig.modeHigh, data.defaultMode == 1);
        setPadding(highBtn, 5, 10);
        highBtn.setActionCommand(ProviderConfig.modeHigh);
        JRadioButton extendedBtn = new JRadioButton(ProviderConfig.modeExtended, data.defaultMode == 2);
        setPadding(extendedBtn, 5, 10);
        extendedBtn.setActionCommand(ProviderConfig.modeExtended);


        template.add(defaultBtn);
        template.add(highBtn);
        template.add(extendedBtn);
        templateGroup = new ButtonGroup();
        templateGroup.add(defaultBtn);
        templateGroup.add(highBtn);
        templateGroup.add(extendedBtn);

        container.add(template);
        setDivision(container);
    }

    /**
     * Generate file
     */
    private void setCodeFile(Container container) {
        //Select build file
        JPanel file = new JPanel();
        file.setLayout(new GridLayout(2, 2));
        file.setBorder(BorderFactory.createTitledBorder("Select Function"));

        //use folder
        folderBox = new JCheckBox("useFolder", data.useFolder);
        setMargin(folderBox, 5, 10);
        file.add(folderBox);

        //use prefix
        prefixBox = new JCheckBox("usePrefix", data.usePrefix);
        setMargin(prefixBox, 5, 10);
        file.add(prefixBox);

        //use null-safety
        nullSafetyBox = new JCheckBox("null-safety", data.nullSafety);
        setMargin(nullSafetyBox, 5, 10);
        file.add(nullSafetyBox);


        container.add(file);
        setDivision(container);
    }


    /**
     * Generate file name and button
     */
    private void setNameAndConfirm(Container container) {
        JPanel nameField = new JPanel();
        nameField.setLayout(new FlowLayout());
        nameField.setBorder(BorderFactory.createTitledBorder("Module Name"));
        nameTextField = new JTextField(30);
        nameTextField.addKeyListener(keyListener);
        nameField.add(nameTextField);
        container.add(nameField);

        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        //Set bottom spacing
        setDivision(container);

        //OK cancel button
        JButton cancel = new JButton("Cancel");
        cancel.setForeground(JBColor.RED);
        cancel.addActionListener(actionListener);

        JButton ok = new JButton("OK");
        ok.setForeground(JBColor.GREEN);
        ok.addActionListener(actionListener);
        menu.add(cancel);
        menu.add(ok);
        container.add(menu);
    }

    private void createFile() {
        String type = templateGroup.getSelection().getActionCommand();
        //deal default value
        if (ProviderConfig.modeDefault.equals(type)) {
            data.defaultMode = 0;
        } else if (ProviderConfig.modeHigh.equals(type)) {
            data.defaultMode = 1;
        } else if (ProviderConfig.modeExtended.equals(type)) {
            data.defaultMode = 2;
        }
        data.useFolder = folderBox.isSelected();
        data.usePrefix = prefixBox.isSelected();
        data.nullSafety = nullSafetyBox.isSelected();


        String name = nameTextField.getText();
        String prefix = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        String folder = "";
        String prefixName = "";

        //Add folder
        if (data.useFolder) {
            folder = "/" + prefix;
        }

        //Add prefix
        if (data.usePrefix) {
            prefixName = prefix + "_";
        }

        switch (type) {
            case ProviderConfig.modeDefault:
                generateDefault(folder, prefixName);
                break;
            case ProviderConfig.modeHigh:
                generateHigh(folder, prefixName);
                break;
            case ProviderConfig.modeExtended:
                generateExtended(folder, prefixName);
                break;
        }
    }

    private void generateDefault(String folder, String prefixName) {
        String path = psiPath + folder;
        generateFile("view.dart", path, prefixName + data.viewFileName.toLowerCase() + ".dart");
        generateFile("provider.dart", path, prefixName + data.logicName.toLowerCase() + ".dart");
    }

    private void generateHigh(String folder, String prefixName) {
        String path = psiPath + folder;
        generateFile("high/view.dart", path, prefixName + data.viewFileName.toLowerCase() + ".dart");
        generateFile("high/provider.dart", path, prefixName + data.logicName.toLowerCase() + ".dart");
        generateFile("high/state.dart", path, prefixName + data.stateName.toLowerCase() + ".dart");
    }

    private void generateExtended(String folder, String prefixName) {
        String path = psiPath + folder;
        generateFile("extended/view.dart", path, prefixName + data.viewFileName.toLowerCase() + ".dart");
        generateFile("extended/provider.dart", path, prefixName + data.logicName.toLowerCase() + ".dart");
        generateFile("extended/state.dart", path, prefixName + data.stateName.toLowerCase() + ".dart");
    }


    private void generateFile(String inputFileName, String filePath, String outFileName) {
        //content deal
        String content = dealContent(inputFileName, outFileName);

        //Write file
        try {
            File folder = new File(filePath);
            // if file doesnt exists, then create it
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(filePath + "/" + outFileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //content need deal
    private String dealContent(String inputFileName, String outFileName) {
        //baseFolder
        String baseFolder = "/templates/";

        //read file
        String content = "";
        try {
            InputStream in = this.getClass().getResourceAsStream(baseFolder + inputFileName);
            content = new String(readStream(in));
        } catch (Exception e) {
        }


        String prefixName = "";
        //Adding a prefix requires modifying the imported class name
        if (data.usePrefix) {
            prefixName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, nameTextField.getText()) + "_";
        }
        // null-safety
        if (!data.nullSafety) {
            content = content.replaceAll("late ", "");
        }
        content = content.replaceAll("provider.dart", prefixName + data.logicName.toLowerCase() + ".dart");
        content = content.replaceAll("state.dart", prefixName + data.stateName.toLowerCase() + ".dart");
        //replace logic
        if (outFileName.contains(data.logicName.toLowerCase())) {
            content = content.replaceAll("Provider", data.logicName);
            content = content.replaceAll("State", data.stateName);
            content = content.replaceAll("state", data.stateName.toLowerCase());
        }
        //replace state
        if (outFileName.contains(data.stateName.toLowerCase())) {
            content = content.replaceAll("State", data.stateName);
        }
        //replace view
        if (outFileName.contains(data.viewFileName.toLowerCase())) {
            content = content.replaceAll("Page", data.viewName);
            content = content.replaceAll("Provider", data.logicName);
            content = content.replaceAll("provider", data.logicName.toLowerCase());
            content = content.replaceAll("\\$nameState", "\\$name" + data.stateName);
            content = content.replaceAll("state", data.stateName.toLowerCase());
        }

        content = content.replaceAll("\\$name", nameTextField.getText());

        return content;
    }

    private byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
                System.out.println(new String(buffer));
            }

        } catch (IOException e) {
        } finally {
            outSteam.close();
            inStream.close();
        }
        return outSteam.toByteArray();
    }


    private final KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) save();
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) dispose();
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Cancel")) {
                dispose();
            } else {
                save();
            }
        }
    };

    private void setPadding(JRadioButton btn, int top, int bottom) {
        btn.setBorder(BorderFactory.createEmptyBorder(top, 10, bottom, 0));
    }

    private void setMargin(JCheckBox btn, int top, int bottom) {
        btn.setBorder(BorderFactory.createEmptyBorder(top, 10, bottom, 0));
    }

    private void setDivision(Container container) {
        //Separate the spacing between modules
        JPanel margin = new JPanel();
        container.add(margin);
    }

    private void dispose() {
        jDialog.dispose();
    }
}
