package br.edu.ifms.lp3.ui;

import br.edu.ifms.lp3.dao.jpa.ClienteDAO;
import br.edu.ifms.lp3.model.Cliente;
import br.edu.ifms.lp3.ui.model.Matriz;
import br.edu.ifms.lp3.ui.model.ModelTableClient;
import br.edu.ifms.lp3.ui.screen.ScreenHelper;
import br.edu.ifms.lp3.util.ManagerClient;
import br.edu.ifms.lp3.util.Validator;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class RegisterScreen extends ScreenHelper {

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

    private JScrollPane scrollpane;
    private JPanel registerPanel;

    private final ButtonGroup radioGroupSex = new ButtonGroup();
    private JTable table;

    private static JButton buttonUpdateRecord;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");

                RegisterScreen window = new RegisterScreen();
                window.mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    private RegisterScreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        mainFrame = new JFrame();
        mainFrame.setMaximumSize(new Dimension(1782, 816));
        mainFrame.setMinimumSize(new Dimension(891, 408));
        mainFrame.setTitle("Cadastro de cliente - 1.0");
        mainFrame.setBounds(100, 100, 891, 408);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(
                new MigLayout("", "[390px,grow][451px,grow]", "[28.00,fill][316px,grow][21.00]"));
        mainFrame.setIconImage(new ImageIcon(RegisterScreen.class.getResource("/img/icone.png")).getImage());

        registerPanel = new JPanel();
        registerPanel.setBorder(makeBorder("Cadastrar"));
        mainFrame.getContentPane().add(registerPanel, "cell 0 1,grow");
        registerPanel.setLayout(new MigLayout("",
                "[54px,grow][109px,grow][109px,grow][30.00]",
                "[20px,grow][20px,grow][20px,grow][24px,grow][24px,grow][23px,grow]"));

        labelName = new JLabel("Nome");
        registerPanel.add(labelName, "cell 0 0,growx,aligny center");

        fieldName = new JTextField();
        fieldName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                Validator validator = new Validator();
                // Se tiver algum texto
                if (validator.checkName(fieldName.getText()))
                    setValidationStyle(labelName, imageName, GREEN, "certo");
                else
                    setValidationStyle(labelName, imageName, RED, "erro");
            }
        });
        registerPanel.add(fieldName, "cell 1 0 2 1,growx,aligny center");
        fieldName.setColumns(10);

        imageCPF = new JLabel();
        registerPanel.add(imageCPF, "cell 3 1,alignx center,aligny center");

        imageName = new JLabel();
        registerPanel.add(imageName, "cell 3 0,alignx center,aligny center");

        labelCPF = new JLabel("CPF");
        registerPanel.add(labelCPF, "cell 0 1,growx,aligny center");

        MaskFormatter maskCPF = null;

        try {
            maskCPF = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fieldCPF = new JFormattedTextField(maskCPF);
        fieldCPF.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                Validator validator = new Validator();
                // Se for um CPF válido
                if (validator.checkCPF(fieldCPF.getText())) {
                    setValidationStyle(labelCPF, imageCPF, GREEN, "certo");
                } else {
                    setValidationStyle(labelCPF, imageCPF, RED, "erro");
                }
            }
        });
        fieldCPF.setHorizontalAlignment(SwingConstants.CENTER);
        fieldCPF.setColumns(10);
        registerPanel.add(fieldCPF, "cell 1 1 2 1,alignx left,aligny center");

        labelPhone = new JLabel("Telefone");
        registerPanel.add(labelPhone, "cell 0 2,growx,aligny center");

        MaskFormatter phoneMask = null;

        try {
            phoneMask = new MaskFormatter("(##) ####-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fieldPhone = new JFormattedTextField(phoneMask);
        fieldPhone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent arg0) {
                Validator validator = new Validator();

                if (validator.checkPhone(fieldPhone.getText())) {
                    setValidationStyle(labelPhone, imagePhone, GREEN, "certo");
                } else {
                    setValidationStyle(labelPhone, imagePhone, RED, "erro");
                }
            }
        });
        fieldPhone.setHorizontalAlignment(SwingConstants.CENTER);
        fieldPhone.setColumns(10);
        registerPanel.add(fieldPhone, "flowx,cell 1 2 2 1,alignx left,aligny center");

        imagePhone = new JLabel();
        registerPanel.add(imagePhone, "cell 3 2,alignx center,aligny center");

        JLabel textoSexo = new JLabel("Sexo");
        registerPanel.add(textoSexo, "cell 0 3,growx,aligny center");

        radioButtonFemale = new JRadioButton("F");
        radioGroupSex.add(radioButtonFemale);
        registerPanel.add(radioButtonFemale, "flowx,cell 1 3,alignx center,aligny center");

        radioButtonMale = new JRadioButton("M");
        radioGroupSex.add(radioButtonMale);
        registerPanel.add(radioButtonMale, "cell 1 3,alignx center,aligny center");

        labelAddress = new JLabel("Endere\u00E7o");
        registerPanel.add(labelAddress, "cell 0 4,growx,aligny center");

        fieldAddress = new JTextField();
        fieldAddress.addFocusListener(new FocusAdapter() {
            // Quando perder o foco
            @Override
            public void focusLost(FocusEvent arg0) {
                Validator validator = new Validator();
                // Se passar nas validações
                if (validator.checkAddress(fieldAddress.getText())) {
                    setValidationStyle(labelAddress, imageAddress, GREEN, "certo");
                } else {
                    setValidationStyle(labelAddress, imageAddress, RED, "erro");
                }
            }
        });
        fieldAddress.setColumns(10);
        registerPanel.add(fieldAddress, "cell 1 4 2 1,growx,aligny center");

        imageAddress = new JLabel();
        registerPanel.add(imageAddress, "cell 3 4,alignx center,aligny center");

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(arg0 -> {
            ManagerClient cadastrando = new ManagerClient();
            // Criando e validando cliente
            Cliente cliente = cadastrando.createValidateClient(
                    fieldName.getText(), fieldAddress.getText(),
                    fieldCPF.getText(), fieldPhone.getText(),
                    radioButtonFemale, radioButtonMale);
            // Se for um cliente valido
            if (cliente != null) {
                // Se foi possível cadastrar o cliente, caso contrario fazer nada
                if (cadastrando.recordClient(cliente, mainFrame)) {
                    // Apaga tudo
                    clearScreen();
                }
            } else {
                showErrorMessage();
            }
        });
        registerPanel.add(botaoCadastrar,
                "flowx,cell 0 5,alignx left,aligny center");

        JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.addActionListener(arg0 -> clearScreen());
        registerPanel.add(botaoLimpar, "flowx,cell 1 5,alignx center,aligny center");

        JButton botaoRemover = new JButton("Remover");
        botaoRemover.addActionListener(arg0 -> {
            ManagerClient cadastrando = new ManagerClient();
            // Criando e validando cliente
            Cliente cliente = cadastrando.createValidateClient(
                    fieldName.getText(), fieldAddress.getText(),
                    fieldCPF.getText(), fieldPhone.getText(),
                    radioButtonFemale, radioButtonMale);
            // Se for um cliente valido
            if (cliente != null) {
                // Se foi possível atualizar o registro do candidato, caso contrario não precisa fazer nada
                if (cadastrando.removeClient(cliente, mainFrame)) {
                    clearScreen();
                }
            } else {
                showErrorMessage();
            }
        });

        registerPanel.add(botaoRemover, "cell 1 5,alignx center,aligny center");

        buttonUpdateRecord = new JButton("Atualizar");
        buttonUpdateRecord.addActionListener(arg0 -> {
            ManagerClient cadastrando = new ManagerClient();

            // Criando e validando cliente
            Cliente cliente = cadastrando.createValidateClient(
                    fieldName.getText(), fieldAddress.getText(),
                    fieldCPF.getText(), fieldPhone.getText(),
                    radioButtonFemale, radioButtonMale);
            // Se for um cliente valido
            if (cliente != null) {
                // Se foi possível atualizar o registro do candidato, caso contrario não precisa fazer nada
                if (cadastrando.updateClient(cliente, mainFrame)) {
                    clearScreen();
                }
            } else {
                showErrorMessage();
            }
        });
        buttonUpdateRecord.setVisible(false);
        registerPanel.add(buttonUpdateRecord, "cell 2 5,alignx right,aligny center");

        JPanel panelSearch = new JPanel();
        panelSearch.setBorder(makeBorder("Buscar"));
        mainFrame.getContentPane().add(panelSearch, "cell 1 1,grow");
        panelSearch.setLayout(new MigLayout("", "[46px,grow][35px][359px,grow]", "[20px][266px,grow]"));

        JLabel labelSearch = new JLabel("Buscar");
        panelSearch.add(labelSearch, "cell 0 0,alignx left,aligny center");

        fieldSearch = new JTextField();
        fieldSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent arg0) {
                // Passando 1 como parâmetro para buscar por nome
                createTable(1);
            }
        });
        panelSearch.add(fieldSearch, "flowx,cell 2 0,alignx left,aligny center");
        fieldSearch.setColumns(10);

        scrollpane = new JScrollPane();
        panelSearch.add(scrollpane, "cell 0 1 3 1,grow");
        // Passando 2 como parâmetro para buscar tudo
        createTable(2);
    }

    /**
     * Método que recebe o textoLabel e a imagemLabel, e os formata de acordo com a cor e imagem recebidas
     *
     * @param textLabel  - Label onde encontra o texto informativo do campo
     * @param imageLabel - Label que recebe imagem para indicar o status do campo
     * @param color      - Cor que o texto deve ficar
     * @param imageName  - Nome da imagem a ser adicionada.
     *                   <p>
     *                   Obs: tem que ser ".png" e estar na pasta "resources/img".
     *                   Exemplo: imageName = icone.
     */
    private void setValidationStyle(JLabel textLabel, JLabel imageLabel, Color color, String imageName) {
        textLabel.setForeground(color);
        imageLabel.setIcon(getIcon(imageName));
    }

    /**
     * Método para retirar formatação dos campos
     */
    private void clearFormatting() {
        // Setando cor dos labels para preto
        labelCPF.setForeground(BLACK);
        labelName.setForeground(BLACK);
        labelAddress.setForeground(BLACK);
        labelPhone.setForeground(BLACK);
        // Setando ícones para nulo
        imageCPF.setIcon(null);
        imageAddress.setIcon(null);
        imageName.setIcon(null);
        imagePhone.setIcon(null);
    }

    /**
     * Método para apagar o conteúdo digitado nas caixas de texto
     */
    private void clearText() {
        // Se o texto não for vazio
        if (!fieldCPF.getText().isEmpty())
            fieldCPF.setText("");
        // Se o texto não for vazio
        if (!fieldName.getText().isEmpty())
            fieldName.setText("");
        // Se o texto não for vazio
        if (!fieldAddress.getText().isEmpty())
            fieldAddress.setText("");
        // Se o telefone não estiver vazio
        if (!fieldPhone.getText().isEmpty())
            fieldPhone.setText("");
        // Se o campo busca não estiver vazio
        if (!fieldSearch.getText().isEmpty())
            fieldSearch.setText("");
        // Limpa os botões de radio
        radioGroupSex.clearSelection();
    }

    /**
     * Método que ira capturar conteúdo da linha selecionada
     */
    private void getDataFromSelectedRow() {
        // pegando o índice da linha que foi selecionada
        int rowNumber = table.getSelectedRow();
        // pegando o modelo de tabela
        ModelTableClient tableModel = (ModelTableClient) table.getModel();
        // pegando os dados
        String name = (String) tableModel.getValueAt(rowNumber, 0);
        String cpf = (String) tableModel.getValueAt(rowNumber, 1);
        String address = (String) tableModel.getValueAt(rowNumber, 2);
        String sexString = (String) tableModel.getValueAt(rowNumber, 3);
        String phone = (String) tableModel.getValueAt(rowNumber, 4);

        // Convertendo para char
        char sex = sexString.charAt(0);
        // Chamando o método que vai setar tudo os dados pegos e por na tela
        setDataOnFields(name, cpf, address, sex, phone);
    }

    /**
     * Método que recebe tudo que foi pego do banco, mas passaram
     * por um tratamento e seleção e seta estes dados na tela de forma que fique
     * visível para o usuário
     *
     * @param name    -  Nome do usuário
     * @param cpf     - CPF do usuário
     * @param address - Endereço do usuário
     * @param sex     - Sexo do usuário
     * @param phone   - Telefone do usuário
     */
    private void setDataOnFields(String name, String cpf, String address, char sex, String phone) {
        // Setando os campos da tela
        fieldName.setText(name);
        fieldCPF.setText(cpf);
        fieldAddress.setText(address);
        fieldPhone.setText(phone);
        // Se for feminino ou masculino
        if (sex == 'f') {
            radioButtonFemale.setSelected(true);
        } else {
            radioButtonMale.setSelected(true);
        }
    }

    /**
     * Método para a criação de tabela e preenchimento da mesma com os dados do
     * banco
     *
     * @param opcao se vai buscar por nome ou buscar tudo do banco
     *              <p>
     *              <table border="1">
     *              <th>opcao 1</th>
     *              <th>opcao 2</th>
     *              <tr>
     *              <td>opção 1, é para buscar conteúdo da tabela<br>
     *              por um determinado nome ou pedaço do<br>
     *              mesmo, no banco de dados</td>
     *              <td>opção 2, é para buscar conteúdo da tabela<br>
     *              por completo e trazer tudo o que encontrar no banco<br>
     *              do banco de dados</td>
     *              </tr>
     *              </table>
     */
    private void createTable(int opcao) {
        // Objeto do cliente DAO
        ClienteDAO clientDAO = new ClienteDAO();

        Matriz matriz = new Matriz();
        // Matriz a ser mostrada na tela
        String[][] data = null;

        if (opcao == 1) {
            data = matriz.mountMatriz(clientDAO.retrieveByName(fieldSearch.getText()));
        } else if (opcao == 2) {
            data = matriz.mountMatriz(clientDAO.retrieveAll());
        }

        // Modelo de tabela que será mostrada com dados e nomes de colunas
        ModelTableClient tableModel = new ModelTableClient(data);

        table = new JTable(tableModel);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Dois cliques seguidos
                if (e.getClickCount() == 2) {
                    // Limpa a formatação
                    clearScreen();
                    // Pega os dados referente a linha selecionada
                    getDataFromSelectedRow();
                    // deixa o botão de atualizar visível
                    buttonUpdateRecord.setVisible(true);
                }
            }
        });
        scrollpane.setViewportView(table);
    }

    /**
     * Método concentrador de outros métodos que realizam ações como: apagar
     * tudo que for texto, formatação e esconder botões especiais da tela
     */
    private void clearScreen() {
        // Apaga o texto
        clearText();
        // Tira as cores e imagens
        clearFormatting();
        // Esconde o botão de atualizar
        buttonUpdateRecord.setVisible(false);
    }

    /**
     * Método para mostrar mensagem que não foi preenchida os campos necessários
     */
    private void showErrorMessage() {
        JOptionPane.showMessageDialog(registerPanel, "Preencha todos os campos corretamente", "ERRO", JOptionPane.ERROR_MESSAGE);
    }
}