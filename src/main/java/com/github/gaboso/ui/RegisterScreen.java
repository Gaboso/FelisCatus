package com.github.gaboso.ui;

import com.github.gaboso.constant.Textual;
import com.github.gaboso.dao.ClienteDAO;
import com.github.gaboso.helper.ScreenHelper;
import com.github.gaboso.model.Cliente;
import com.github.gaboso.ui.model.Matriz;
import com.github.gaboso.ui.model.ModelTableClient;
import com.github.gaboso.util.ManagerClient;
import com.github.gaboso.util.Validator;
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class RegisterScreen extends ScreenHelper {

    private static final Logger LOGGER = Logger.getLogger(RegisterScreen.class);
    private static final int SEARCH_BY_NAME = 1;
    private static final int SEARCH_ALL = 2;

    private JFrame mainFrame;
    private JTextField fieldName;
    private JFormattedTextField fieldCPF;
    private JFormattedTextField fieldPhone;
    private JTextField fieldAddress;
    private JTextField fieldSearch;

    private JLabel labelName;
    private JLabel labelCPF;
    private JLabel labelPhone;
    private JLabel labelAddress;

    private JLabel imageCPF;
    private JLabel imageName;
    private JLabel imagePhone;
    private JLabel imageAddress;

    private JRadioButton radioButtonFemale;
    private JRadioButton radioButtonMale;

    private JScrollPane scrollPane;

    private final ButtonGroup radioGroupSex = new ButtonGroup();
    private JTable table;

    private JButton buttonUpdate;
    private JButton removeButton;

    /**
     * Create the application.
     */
    private RegisterScreen() {
        initialize();
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                RegisterScreen window = new RegisterScreen();
                window.mainFrame.setVisible(true);
            } catch (Exception e) {
                LOGGER.error(e);
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mainFrame = new JFrame();
        mainFrame.setMaximumSize(new Dimension(1782, 816));
        mainFrame.setMinimumSize(new Dimension(891, 408));
        mainFrame.setTitle(Textual.TITLE);
        mainFrame.setBounds(100, 100, 891, 408);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(
                new MigLayout("", "[390px,grow][451px,grow]", "[28.00,fill][316px,grow][21.00]"));
        mainFrame.setIconImage(getImageIcon("icone").getImage());

        JPanel registerPanel = new JPanel();
        registerPanel.setBorder(makeBorder(Textual.CADASTRAR));
        mainFrame.getContentPane().add(registerPanel, "cell 0 1,grow");
        registerPanel.setLayout(new MigLayout("", "[54px,grow][109px,grow][109px,grow][30.00]",
                "[20px,grow][20px,grow][20px,grow][24px,grow][24px,grow][23px,grow]"));

        labelName = new JLabel(Textual.NOME);
        registerPanel.add(labelName, "cell 0 0,growx,aligny center");

        fieldName = new JTextField();
        fieldName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                Validator validator = new Validator();
                // Se tiver algum texto
                if (validator.checkName(fieldName.getText()))
                    setValidationStyle(labelName, imageName, GREEN, Textual.CORRETO_24);
                else
                    setValidationStyle(labelName, imageName, RED, Textual.INCORRETO_24);
            }
        });
        registerPanel.add(fieldName, "cell 1 0 2 1,growx,aligny center");
        fieldName.setColumns(10);

        imageCPF = new JLabel();
        registerPanel.add(imageCPF, "cell 3 1,alignx center,aligny center");

        imageName = new JLabel();
        registerPanel.add(imageName, "cell 3 0,alignx center,aligny center");

        labelCPF = new JLabel(Textual.CPF);
        registerPanel.add(labelCPF, "cell 0 1,growx,aligny center");

        MaskFormatter maskCPF = null;

        try {
            maskCPF = new MaskFormatter(Textual.CPF_MASK);
        } catch (ParseException e) {
            LOGGER.error(e);
        }

        fieldCPF = new JFormattedTextField(maskCPF);
        fieldCPF.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                Validator validator = new Validator();
                // Se for um CPF válido
                if (validator.checkCPF(fieldCPF.getText())) {
                    setValidationStyle(labelCPF, imageCPF, GREEN, Textual.CORRETO_24);
                } else {
                    setValidationStyle(labelCPF, imageCPF, RED, Textual.INCORRETO_24);
                }
            }
        });
        fieldCPF.setHorizontalAlignment(SwingConstants.CENTER);
        fieldCPF.setColumns(10);
        registerPanel.add(fieldCPF, "cell 1 1 2 1,alignx left,aligny center");

        labelPhone = new JLabel(Textual.TELEFONE);
        registerPanel.add(labelPhone, "cell 0 2,growx,aligny center");

        MaskFormatter phoneMask = null;

        try {
            phoneMask = new MaskFormatter(Textual.PHONE_MASK);
        } catch (ParseException e) {
            LOGGER.error(e);
        }

        fieldPhone = new JFormattedTextField(phoneMask);
        fieldPhone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                whenPhoneLostFocus();
            }
        });
        fieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
        fieldPhone.setColumns(10);
        registerPanel.add(fieldPhone, "flowx,cell 1 2 2 1,alignx left,aligny center");

        imagePhone = new JLabel();
        registerPanel.add(imagePhone, "cell 3 2,alignx center,aligny center");

        JLabel textoSexo = new JLabel(Textual.SEXO);
        registerPanel.add(textoSexo, "cell 0 3,growx,aligny center");

        radioButtonFemale = new JRadioButton("F");
        radioGroupSex.add(radioButtonFemale);
        registerPanel.add(radioButtonFemale, "flowx,cell 1 3,alignx center,aligny center");

        radioButtonMale = new JRadioButton("M");
        radioGroupSex.add(radioButtonMale);
        registerPanel.add(radioButtonMale, "cell 1 3,alignx center,aligny center");

        labelAddress = new JLabel(Textual.ENDERECO);
        registerPanel.add(labelAddress, "cell 0 4,growx,aligny center");

        fieldAddress = new JTextField();
        fieldAddress.addFocusListener(new FocusAdapter() {
            // Quando perder o foco
            @Override
            public void focusLost(FocusEvent arg0) {
                whenAddressLostFocus();
            }
        });
        fieldAddress.setColumns(10);
        registerPanel.add(fieldAddress, "cell 1 4 2 1,growx,aligny center");

        imageAddress = new JLabel();
        registerPanel.add(imageAddress, "cell 3 4,alignx center,aligny center");

        JButton saveButton = new JButton(Textual.CADASTRAR);
        saveButton.addActionListener(arg0 -> whenSaveButtonIsPressed());
        registerPanel.add(saveButton, "flowx,cell 0 5,alignx left,aligny center");

        JButton clearButton = new JButton(Textual.LIMPAR);
        clearButton.addActionListener(arg0 -> clearScreen());
        registerPanel.add(clearButton, "flowx,cell 1 5,alignx center,aligny center");

        removeButton = new JButton(Textual.REMOVER);
        removeButton.addActionListener(arg0 -> whenRemoveButtonIsPressed());
        removeButton.setVisible(false);
        registerPanel.add(removeButton, "cell 1 5,alignx center,aligny center");

        buttonUpdate = new JButton(Textual.ATUALIZAR);
        buttonUpdate.addActionListener(arg0 -> whenUpdateButtonIsPressed());
        buttonUpdate.setVisible(false);
        registerPanel.add(buttonUpdate, "cell 2 5,alignx right,aligny center");

        JPanel panelSearch = new JPanel();
        panelSearch.setBorder(makeBorder(Textual.BUSCAR));
        mainFrame.getContentPane().add(panelSearch, "cell 1 1,grow");
        panelSearch.setLayout(new MigLayout("", "[46px,grow][35px][359px,grow]", "[20px][266px,grow]"));

        JLabel labelSearch = new JLabel(Textual.BUSCAR);
        panelSearch.add(labelSearch, "cell 0 0,alignx left,aligny center");

        fieldSearch = new JTextField();
        fieldSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                refreshTable(SEARCH_BY_NAME);
            }
        });
        panelSearch.add(fieldSearch, "flowx,cell 2 0,alignx left,aligny center");
        fieldSearch.setColumns(10);

        scrollPane = new JScrollPane();
        panelSearch.add(scrollPane, "cell 0 1 3 1,grow");
        refreshTable(SEARCH_ALL);
    }

    private void whenPhoneLostFocus() {
        Validator validator = new Validator();

        if (validator.checkPhone(fieldPhone.getText())) {
            setValidationStyle(labelPhone, imagePhone, GREEN, Textual.CORRETO_24);
        } else {
            setValidationStyle(labelPhone, imagePhone, RED, Textual.INCORRETO_24);
        }
    }

    private void whenAddressLostFocus() {
        Validator validator = new Validator();

        if (validator.checkAddress(fieldAddress.getText())) {
            setValidationStyle(labelAddress, imageAddress, GREEN, Textual.CORRETO_24);
        } else {
            setValidationStyle(labelAddress, imageAddress, RED, Textual.INCORRETO_24);
        }
    }

    private void whenSaveButtonIsPressed() {
        ManagerClient managerClient = new ManagerClient();

        Cliente cliente = managerClient.createValidateClient(
                fieldName.getText(), fieldAddress.getText(),
                fieldCPF.getText(), fieldPhone.getText(),
                radioButtonFemale, radioButtonMale);

        if (cliente != null) {

            if (managerClient.recordClient(cliente, mainFrame)) {
                clearScreen();
                refreshTable(SEARCH_ALL);
            }
        } else {
            showMessageError(mainFrame, Textual.PREENCHA_OS_CAMPOS);
        }
    }

    private void whenRemoveButtonIsPressed() {
        ManagerClient managerClient = new ManagerClient();

        Cliente cliente = managerClient.createValidateClient(
                fieldName.getText(), fieldAddress.getText(),
                fieldCPF.getText(), fieldPhone.getText(),
                radioButtonFemale, radioButtonMale);

        if (cliente != null) {
            if (managerClient.removeClient(cliente, mainFrame)) {
                clearScreen();
                refreshTable(SEARCH_ALL);
            }
        } else {
            showMessageError(mainFrame, Textual.PREENCHA_OS_CAMPOS);
        }
    }

    private void whenUpdateButtonIsPressed() {
        ManagerClient managerClient = new ManagerClient();

        Cliente cliente = managerClient.createValidateClient(
                fieldName.getText(), fieldAddress.getText(),
                fieldCPF.getText(), fieldPhone.getText(),
                radioButtonFemale, radioButtonMale);

        if (cliente != null) {
            if (managerClient.updateClient(cliente, mainFrame)) {
                clearScreen();
                refreshTable(SEARCH_ALL);
            }
        } else {
            showMessageError(mainFrame, Textual.PREENCHA_OS_CAMPOS);
        }
    }

    private void setValidationStyle(JLabel textLabel, JLabel imageLabel, Color color, String imageName) {
        textLabel.setForeground(color);
        imageLabel.setIcon(getImageIcon(imageName));
    }

    private void clearFormatting() {
        labelCPF.setForeground(BLACK);
        labelName.setForeground(BLACK);
        labelAddress.setForeground(BLACK);
        labelPhone.setForeground(BLACK);

        imageCPF.setIcon(null);
        imageAddress.setIcon(null);
        imageName.setIcon(null);
        imagePhone.setIcon(null);
    }

    private void clearText() {
        if (!fieldCPF.getText().isEmpty())
            fieldCPF.setText("");

        if (!fieldName.getText().isEmpty())
            fieldName.setText("");

        if (!fieldAddress.getText().isEmpty())
            fieldAddress.setText("");

        if (!fieldPhone.getText().isEmpty())
            fieldPhone.setText("");

        if (!fieldSearch.getText().isEmpty())
            fieldSearch.setText("");

        radioGroupSex.clearSelection();
    }

    private void getDataFromSelectedRow() {
        int rowNumber = table.getSelectedRow();

        ModelTableClient tableModel = (ModelTableClient) table.getModel();

        String name = (String) tableModel.getValueAt(rowNumber, 0);
        String cpf = (String) tableModel.getValueAt(rowNumber, 1);
        String address = (String) tableModel.getValueAt(rowNumber, 2);
        String sexString = (String) tableModel.getValueAt(rowNumber, 3);
        String phone = (String) tableModel.getValueAt(rowNumber, 4);

        char sex = sexString.charAt(0);

        setDataOnFields(name, cpf, address, sex, phone);
    }

    private void setDataOnFields(String name, String cpf, String address, char sex, String phone) {
        fieldName.setText(name);
        fieldCPF.setText(cpf);
        fieldAddress.setText(address);
        fieldPhone.setText(phone);

        if (sex == 'f') {
            radioButtonFemale.setSelected(true);
        } else {
            radioButtonMale.setSelected(true);
        }
    }

    private void refreshTable(int selectedOption) {
        ClienteDAO clientDAO = new ClienteDAO();

        Matriz matriz = new Matriz();
        String[][] data = null;

        if (selectedOption == SEARCH_BY_NAME) {
            data = matriz.mountMatriz(clientDAO.retrieveByName(fieldSearch.getText()));
        } else if (selectedOption == SEARCH_ALL) {
            data = matriz.mountMatriz(clientDAO.retrieveAll());
        }

        ModelTableClient tableModel = new ModelTableClient(data);

        table = new JTable(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    clearScreen();
                    getDataFromSelectedRow();
                    buttonUpdate.setVisible(true);
                    removeButton.setVisible(true);
                }
            }
        });
        scrollPane.setViewportView(table);
    }

    private void clearScreen() {
        clearText();
        clearFormatting();
        buttonUpdate.setVisible(false);
        removeButton.setVisible(false);
    }

}