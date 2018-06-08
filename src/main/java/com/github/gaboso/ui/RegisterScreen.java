package com.github.gaboso.ui;

import com.github.gaboso.constant.Textual;
import com.github.gaboso.dao.UserDAO;
import com.github.gaboso.helper.ScreenHelper;
import com.github.gaboso.model.User;
import com.github.gaboso.ui.model.Matrix;
import com.github.gaboso.ui.model.ModelTableUser;
import com.github.gaboso.util.ManagerClient;
import com.github.gaboso.util.Validator;
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        registerPanel.setBorder(makeBorder(Textual.FORM));
        mainFrame.getContentPane().add(registerPanel, "cell 0 1,grow");
        registerPanel.setLayout(new MigLayout("", "[54px,grow][109px,grow][109px,grow][30.00]",
                "[20px,grow][20px,grow][20px,grow][24px,grow][24px,grow][23px,grow]"));

        labelName = new JLabel(Textual.NAME);
        registerPanel.add(labelName, "cell 0 0,growx,aligny center");

        fieldName = new JTextField();
        fieldName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                boolean nameIsValid = Validator.name(fieldName.getText());
                validateField(nameIsValid, labelName, imageName);
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
                boolean cpfIsValid = Validator.cpf(fieldCPF.getText());
                validateField(cpfIsValid, labelCPF, imageCPF);
            }
        });
        fieldCPF.setHorizontalAlignment(SwingConstants.CENTER);
        fieldCPF.setColumns(10);
        registerPanel.add(fieldCPF, "cell 1 1 2 1,alignx left,aligny center");

        labelPhone = new JLabel(Textual.PHONE);
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
                boolean phoneIsValid = Validator.phone(fieldPhone.getText());
                validateField(phoneIsValid, labelPhone, imagePhone);
            }
        });
        fieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
        fieldPhone.setColumns(10);
        registerPanel.add(fieldPhone, "flowx,cell 1 2 2 1,alignx left,aligny center");

        imagePhone = new JLabel();
        registerPanel.add(imagePhone, "cell 3 2,alignx center,aligny center");

        JLabel labelSex = new JLabel(Textual.SEX);
        registerPanel.add(labelSex, "cell 0 3,growx,aligny center");

        radioButtonFemale = new JRadioButton("F");
        radioGroupSex.add(radioButtonFemale);
        registerPanel.add(radioButtonFemale, "flowx,cell 1 3,alignx center,aligny center");

        radioButtonMale = new JRadioButton("M");
        radioGroupSex.add(radioButtonMale);
        registerPanel.add(radioButtonMale, "cell 1 3,alignx center,aligny center");

        labelAddress = new JLabel(Textual.ADDRESS);
        registerPanel.add(labelAddress, "cell 0 4,growx,aligny center");

        fieldAddress = new JTextField();
        fieldAddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                boolean addressIsValid = Validator.address(fieldAddress.getText());
                validateField(addressIsValid, labelAddress, imageAddress);
            }
        });
        fieldAddress.setColumns(10);
        registerPanel.add(fieldAddress, "cell 1 4 2 1,growx,aligny center");

        imageAddress = new JLabel();
        registerPanel.add(imageAddress, "cell 3 4,alignx center,aligny center");

        JButton saveButton = new JButton(Textual.CONFIRM);
        saveButton.addActionListener(arg0 -> whenSaveButtonIsPressed());
        registerPanel.add(saveButton, "flowx,cell 0 5,alignx left,aligny center");

        JButton clearButton = new JButton(Textual.CLEAR);
        clearButton.addActionListener(arg0 -> clearScreen());
        registerPanel.add(clearButton, "flowx,cell 1 5,alignx center,aligny center");

        removeButton = new JButton(Textual.REMOVE);
        removeButton.addActionListener(arg0 -> whenRemoveButtonIsPressed());
        removeButton.setVisible(false);
        registerPanel.add(removeButton, "cell 1 5,alignx center,aligny center");

        buttonUpdate = new JButton(Textual.UPDATE);
        buttonUpdate.addActionListener(arg0 -> whenUpdateButtonIsPressed());
        buttonUpdate.setVisible(false);
        registerPanel.add(buttonUpdate, "cell 2 5,alignx right,aligny center");

        JPanel panelSearch = new JPanel();
        panelSearch.setBorder(makeBorder(Textual.SEARCH));
        mainFrame.getContentPane().add(panelSearch, "cell 1 1,grow");
        panelSearch.setLayout(new MigLayout("", "[46px,grow][35px][359px,grow]", "[20px][266px,grow]"));

        JLabel labelSearch = new JLabel(Textual.SEARCH);
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

    private void whenSaveButtonIsPressed() {
        ManagerClient managerClient = new ManagerClient();

        User user = managerClient.newValidUser(
                fieldName.getText(), fieldAddress.getText(),
                fieldCPF.getText(), fieldPhone.getText(),
                radioButtonFemale, radioButtonMale);

        if (user != null) {
            if (managerClient.save(user, mainFrame)) {
                clearScreen();
                refreshTable(SEARCH_ALL);
            }
        } else {
            showErrorMessage(mainFrame, Textual.FILL_FIELDS);
        }
    }

    private void whenRemoveButtonIsPressed() {
        ManagerClient managerClient = new ManagerClient();

        User user = managerClient.newValidUser(
                fieldName.getText(), fieldAddress.getText(),
                fieldCPF.getText(), fieldPhone.getText(),
                radioButtonFemale, radioButtonMale);

        if (user != null) {
            if (managerClient.remove(user, mainFrame)) {
                clearScreen();
                refreshTable(SEARCH_ALL);
            }
        } else {
            showErrorMessage(mainFrame, Textual.FILL_FIELDS);
        }
    }

    private void whenUpdateButtonIsPressed() {
        ManagerClient managerClient = new ManagerClient();

        User user = managerClient.newValidUser(
                fieldName.getText(), fieldAddress.getText(),
                fieldCPF.getText(), fieldPhone.getText(),
                radioButtonFemale, radioButtonMale);

        if (user != null) {
            if (managerClient.update(user, mainFrame)) {
                clearScreen();
                refreshTable(SEARCH_ALL);
            }
        } else {
            showErrorMessage(mainFrame, Textual.FILL_FIELDS);
        }
    }

    private void validateField(boolean result, JLabel label, JLabel image) {
        if (result) {
            setValidationSuccess(label, image);
        } else {
            setValidationError(label, image);
        }
    }

    private void setValidationError(JLabel textLabel, JLabel imageLabel) {
        setValidationStyle(textLabel, imageLabel, RED, Textual.INCORRETO_24);
    }

    private void setValidationSuccess(JLabel textLabel, JLabel imageLabel) {
        setValidationStyle(textLabel, imageLabel, GREEN, Textual.CORRETO_24);
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

        ModelTableUser tableModel = (ModelTableUser) table.getModel();

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
        UserDAO userDAO = new UserDAO();

        String[][] data = null;

        if (selectedOption == SEARCH_BY_NAME) {
            data = Matrix.create(userDAO.findByName(fieldSearch.getText()));
        } else if (selectedOption == SEARCH_ALL) {
            data = Matrix.create(userDAO.findAll());
        }

        ModelTableUser tableModel = new ModelTableUser(data);

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