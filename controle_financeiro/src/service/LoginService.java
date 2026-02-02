package service;

import dao.UsuarioDao;

public class LoginService {
    
    private UsuarioDao dao = new UsuarioDao();

    public boolean autenticar(String usuario, String senha) {
        // Aqui ocorre a validação do banco
        return dao.validarLogin(usuario, senha);
        
     
    }

	public void cadastrarNovoUsuario(String usuario, String senha) {
		dao.cadastrar(usuario, senha);
		// TODO Auto-generated method stub
		
	}
}