package view.login;

import javax.swing.*;
import java.awt.*;
import service.LoginService;

public class CadastroUsuarioView extends JFrame {

    private JTextField txtUser = new JTextField();
    private JPasswordField txtPass = new JPasswordField();
    private LoginService service = new LoginService();

    public CadastroUsuarioView() {
        setTitle("Novo Usuário");
        setSize(300, 250);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblUser = new JLabel("Usuário:");
        lblUser.setBounds(30, 20, 100, 25);
        add(lblUser);

        txtUser.setBounds(30, 45, 220, 30);
        add(txtUser);

        JLabel lblPass = new JLabel("Senha:");
        lblPass.setBounds(30, 85, 100, 25);
        add(lblPass);

        txtPass.setBounds(30, 110, 220, 30);
        add(txtPass);

        JButton btnSalvar = new JButton("Cadastrar");
        btnSalvar.setBounds(30, 160, 220, 35);
        btnSalvar.addActionListener(e -> {
            String usuario = txtUser.getText();
            String senha = new String(txtPass.getPassword());
            
            if(!usuario.isEmpty() && !senha.isEmpty()) {
                service.cadastrarNovoUsuario(usuario, senha);
                JOptionPane.showMessageDialog(this, "Usuário " + usuario + " cadastrado!");
                dispose(); // Fecha a tela de cadastro
            } else {
                JOptionPane.showMessageDialog(this, "Preencha tudo!");
            }
        });
        add(btnSalvar);
    }
}