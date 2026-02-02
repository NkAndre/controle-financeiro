package app;

import view.login.LoginView;

public class Main {
    public static void main(String[] args) {
        new LoginView().setVisible(true);
        
       /* System.out.println("Testando conexão...");
        dao.ConnectionFactory.getConnection();
        System.out.println("Conectado com sucesso ao MySQL!");*/
    }
}
