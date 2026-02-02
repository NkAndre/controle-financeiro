package view.lancamento;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import model.Categoria;
import model.Lancamento;
import model.TipoLancamento;
import service.LancamentoService;
import view.dashboard.DashboardView;

public class LancamentoView extends JFrame {

	private JTextField txtDescricao, txtValor;
	private JComboBox<TipoLancamento> cbTipo;
	private JComboBox<Categoria> cbCategoria;
	private JButton btnSalvar;

	private LancamentoService service = new LancamentoService();
	private DashboardView dashboard;

	public LancamentoView(DashboardView dash) {
		this.dashboard = dash;
		configurarJanela();
		inicializarComponentes();
		carregarCategorias();
	}

	private void configurarJanela() {
		setTitle("Novo Lançamento");
		setSize(400, 450);
		setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(new Color(245, 245, 245));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void inicializarComponentes() {
		Font labelFont = new Font("SansSerif", Font.BOLD, 12);

		JLabel lblDesc = new JLabel("Descrição:");
		lblDesc.setBounds(35, 25, 100, 25);
		lblDesc.setFont(labelFont);
		add(lblDesc);

		txtDescricao = new JTextField();
		txtDescricao.setBounds(35, 50, 315, 35);
		add(txtDescricao);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(35, 95, 100, 25);
		lblValor.setFont(labelFont);
		add(lblValor);

		txtValor = new JTextField();
		txtValor.setBounds(35, 120, 315, 35);
		add(txtValor);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(35, 165, 100, 25);
		lblTipo.setFont(labelFont);
		add(lblTipo);

		cbTipo = new JComboBox<>(TipoLancamento.values());
		cbTipo.setBounds(35, 190, 150, 35);
		add(cbTipo);

		JLabel lblCat = new JLabel("Categoria:");
		lblCat.setBounds(200, 165, 100, 25);
		lblCat.setFont(labelFont);
		add(lblCat);

		cbCategoria = new JComboBox<>();
		cbCategoria.setBounds(200, 190, 150, 35);
		add(cbCategoria);

		btnSalvar = new JButton("SALVAR LANÇAMENTO");
		btnSalvar.setBounds(35, 280, 315, 50);
		btnSalvar.setBackground(new Color(39, 174, 96));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSalvar.setFocusPainted(false);
		btnSalvar.addActionListener(e -> salvar());
		add(btnSalvar);
	}

	private void carregarCategorias() {
		cbCategoria.addItem(new Categoria(1L, "Alimentação"));
		cbCategoria.addItem(new Categoria(2L, "Transporte"));
		cbCategoria.addItem(new Categoria(3L, "Lazer"));
		cbCategoria.addItem(new Categoria(4L, "Saúde"));
	}

	private void salvar() {
		try {
			if (txtDescricao.getText().trim().isEmpty() || txtValor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
				return;
			}

			Lancamento lanc = new Lancamento();
			lanc.setDescricao(txtDescricao.getText());
			lanc.setValor(Double.parseDouble(txtValor.getText().replace(",", ".")));
			lanc.setTipo((TipoLancamento) cbTipo.getSelectedItem());
			lanc.setCategoria((Categoria) cbCategoria.getSelectedItem());
			lanc.setData(LocalDate.now());

			service.adicionar(lanc);

			// Chama o método no Dashboard para atualizar a tela principal
			dashboard.atualizarSaldo("");

			JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
			dispose();

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Valor inválido!");
		}
	}
}