package service;

import java.util.List;
import dao.LancamentoDao;
import model.Lancamento;
import model.TipoLancamento;

public class LancamentoService {

    private LancamentoDao dao = new LancamentoDao();

    public void adicionar(Lancamento l) {
        dao.salvar(l);
    }

    public List<Lancamento> listar() {
        return dao.listarTodos();
    }

    public double calcularSaldo() {
        List<Lancamento> lista = dao.listarTodos();
        double saldo = 0;
        for (Lancamento l : lista) {
            if (l.getTipo() == TipoLancamento.ENTRADA) saldo += l.getValor();
            else saldo -= l.getValor();
        }
        return saldo;
    }

    public void excluir(int id) {
        dao.excluir(id);
    }
}
