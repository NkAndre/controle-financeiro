package view.dashboard;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Lancamento;
import model.TipoLancamento;
import service.LancamentoService;
import view.lancamento.LancamentoView;
import view.login.LoginView;

public class DashboardView extends JFrame {

    private JLabel lblSaldo;
    private JTable tabela;
    private DefaultTableModel tableModel;
    private JComboBox<String> cbFiltro;
    private LancamentoService service = new LancamentoService();
    private int larguraTela = Toolkit.getDefaultToolkit().getScreenSize().width;

    public DashboardView() {
        configurarJanela();
        inicializarMenu();
        inicializarComponentes();
        atualizarDados();
    }

    private void configurarJanela() {
        this.setSize(1200, 800);
        this.setTitle("Controle Financeiro - Gestão Profissional");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        getContentPane().setBackground(new Color(245, 245, 245));
        this.setLayout(null); 
    }

    private void inicializarMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu arq = new JMenu("Arquivo");
        JMenuItem logout = new JMenuItem("Sair (Logout)");
        logout.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(this, "Voltar para o Login?") == JOptionPane.YES_OPTION) {
                new LoginView().setVisible(true);
                this.dispose();
            }
        });
        JMenuItem fechar = new JMenuItem("Fechar Sistema");
        fechar.addActionListener(e -> System.exit(0));
        arq.add(logout); arq.addSeparator(); arq.add(fechar);
        bar.add(arq); setJMenuBar(bar);
    }

    private void inicializarComponentes() {
        int larguraConteudo = 850;
        int centroX = (larguraTela / 2) - (larguraConteudo / 2);
        int xCard = (larguraTela / 2) - (320 / 2);

        JLabel titulo = new JLabel("Controle Financeiro", SwingConstants.CENTER);
        titulo.setBounds(centroX, 50, larguraConteudo, 45);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        add(titulo);

        JPanel cardSaldo = new JPanel();
        cardSaldo.setBounds(xCard, 120, 320, 100);
        cardSaldo.setBackground(Color.WHITE);
        cardSaldo.setLayout(null);
        cardSaldo.setBorder(BorderFactory.createLineBorder(new Color(210, 210, 210), 2));
        add(cardSaldo);

        lblSaldo = new JLabel("Saldo: R$ 0,00", SwingConstants.CENTER);
        lblSaldo.setBounds(0, 35, 320, 30);
        lblSaldo.setFont(new Font("SansSerif", Font.BOLD, 24));
        cardSaldo.add(lblSaldo);

        JButton btnNovo = new JButton("+ Novo Lançamento");
        btnNovo.setBounds(xCard, 235, 320, 45);
        btnNovo.setBackground(new Color(52, 152, 219));
        btnNovo.setForeground(Color.WHITE);
        btnNovo.setFont(new Font("SansSerif", Font.BOLD, 15));
        btnNovo.addActionListener(e -> new LancamentoView(this).setVisible(true));
        add(btnNovo);

        JLabel lblFiltrar = new JLabel("Filtrar por:");
        lblFiltrar.setBounds(centroX, 300, 100, 25);
        lblFiltrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(lblFiltrar);

        cbFiltro = new JComboBox<>(new String[]{"Todos", "Entradas", "Saídas"});
        cbFiltro.setBounds(centroX + 85, 300, 150, 25);
        cbFiltro.addActionListener(e -> atualizarDados());
        add(cbFiltro);

        String[] colunas = {"ID", "Descrição", "Valor", "Tipo", "Categoria", "Data"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(tableModel);
        tabela.setRowHeight(28);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(centroX, 335, larguraConteudo, 300);
        add(scroll);

        JButton btnExcluir = new JButton("Excluir Selecionado");
        btnExcluir.setBounds(xCard, 650, 320, 45);
        btnExcluir.setBackground(new Color(231, 76, 60));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnExcluir.addActionListener(e -> excluirRegistro());
        add(btnExcluir);
    }

    public void atualizarDados() {
        double saldoTotal = service.calcularSaldo();
        lblSaldo.setText(String.format("Saldo: R$ %.2f", saldoTotal));
        lblSaldo.setForeground(saldoTotal < 0 ? Color.RED : new Color(39, 174, 96));

        tableModel.setRowCount(0);
        List<Lancamento> lista = service.listar();
        String filtro = (String) cbFiltro.getSelectedItem();

        for (Lancamento l : lista) {
            boolean mostrar = false;
            if (filtro.equals("Todos")) mostrar = true;
            else if (filtro.equals("Entradas") && l.getTipo() == TipoLancamento.ENTRADA) mostrar = true;
            else if (filtro.equals("Saídas") && l.getTipo() == TipoLancamento.SAIDA) mostrar = true;

            if (mostrar) {
                Object[] linha = {
                    l.getId(), // Usando o ID real do banco
                    l.getDescricao(),
                    String.format("R$ %.2f", l.getValor()),
                    l.getTipo(),
                    (l.getCategoria() != null ? l.getCategoria().getNome() : "---"),
                    l.getData()
                };
                tableModel.addRow(linha);
            }
        }
    }

    private void excluirRegistro() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            if (JOptionPane.showConfirmDialog(this, "Excluir permanentemente do banco?") == JOptionPane.YES_OPTION) {
                // Pega o ID da coluna 0 (ID do banco)
                int idBanco = (int) tabela.getValueAt(linha, 0);
                service.excluir(idBanco); 
                atualizarDados();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item!");
        }
    }

    public void atualizarSaldo(String texto) {
        atualizarDados();
    }
}