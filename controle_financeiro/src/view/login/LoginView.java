package view.login;

import javax.swing.*;
import java.awt.*;
import service.LoginService;
import view.dashboard.DashboardView;
import model.Sessao; // Importante para salvar quem logou

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    private JButton btnCriarConta;
    private LoginService loginService = new LoginService();

    public LoginView() {
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Sistema Financeiro - Login");
        setSize(1000, 700); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(236, 240, 241)); 
        setLayout(null);
        setExtendedState(MAXIMIZED_BOTH); 
    }

    private void inicializarComponentes() {
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        int larguraCard = 400;
        int alturaCard = 480; 
        int xCentro = (screenWidth / 2) - (larguraCard / 2);
        int yCentro = (screenHeight / 2) - (alturaCard / 2) - 50;

        JPanel card = new JPanel();
        card.setBounds(xCentro, yCentro, larguraCard, alturaCard);
        card.setBackground(Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        add(card);

        JLabel lblTitulo = new JLabel("LOGIN", SwingConstants.CENTER);
        lblTitulo.setBounds(0, 40, larguraCard, 40);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(44, 62, 80));
        card.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Acesse sua conta financeira", SwingConstants.CENTER);
        lblSubtitulo.setBounds(0, 80, larguraCard, 20);
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblSubtitulo.setForeground(Color.GRAY);
        card.add(lblSubtitulo);

        JLabel lblUser = new JLabel("Usuário");
        lblUser.setBounds(50, 130, 300, 20);
        lblUser.setFont(new Font("SansSerif", Font.BOLD, 12));
        card.add(lblUser);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(50, 155, 300, 40);
        txtUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        card.add(txtUsuario);

        JLabel lblPass = new JLabel("Senha");
        lblPass.setBounds(50, 215, 300, 20);
        lblPass.setFont(new Font("SansSerif", Font.BOLD, 12));
        card.add(lblPass);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(50, 240, 300, 40);
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        card.add(txtSenha);

        btnEntrar = new JButton("ENTRAR NO SISTEMA");
        btnEntrar.setBounds(50, 320, 300, 50);
        btnEntrar.setBackground(new Color(52, 152, 219));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnEntrar.setFocusPainted(false);
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEntrar.addActionListener(e -> autenticar());
        card.add(btnEntrar);

        btnCriarConta = new JButton("Não tem conta? Cadastre-se aqui");
        btnCriarConta.setBounds(50, 385, 300, 30);
        btnCriarConta.setFont(new Font("SansSerif", Font.PLAIN, 12));
        btnCriarConta.setForeground(new Color(52, 152, 219));
        btnCriarConta.setContentAreaFilled(false);
        btnCriarConta.setBorderPainted(false);
        btnCriarConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCriarConta.addActionListener(e -> new CadastroUsuarioView().setVisible(true));
        card.add(btnCriarConta);
        
        getRootPane().setDefaultButton(btnEntrar);
    }

    private void autenticar() {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        if (loginService.autenticar(usuario, senha)) {
           
            Sessao.usuarioLogado = usuario; // Salva o nome do usuário globalmente
            // ---------------------------
            
            new DashboardView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}