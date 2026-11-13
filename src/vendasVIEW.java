import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends JFrame {

    private final DefaultTableModel modeloTabela;
    private final JTable tabelaVendas;

    public vendasVIEW() {
        setTitle("Produtos vendidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Lista de Produtos Vendidos", SwingConstants.CENTER);
        titulo.setFont(new java.awt.Font("Lucida Fax", java.awt.Font.PLAIN, 18));

        modeloTabela = new DefaultTableModel(
                new Object[]{"ID", "Nome", "Valor", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaVendas = new JTable(modeloTabela);

        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(evento -> listarProdutosVendidos());

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(evento -> dispose());

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btnAtualizar);
        botoes.add(btnVoltar);

        setLayout(new BorderLayout(12, 12));
        add(titulo, BorderLayout.NORTH);
        add(new JScrollPane(tabelaVendas), BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        setSize(560, 360);
        setLocationRelativeTo(null);
        listarProdutosVendidos();
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            ArrayList<ProdutosDTO> produtos = produtosdao.listarProdutosVendidos();

            modeloTabela.setRowCount(0);
            for (ProdutosDTO produto : produtos) {
                modeloTabela.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
                });
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível carregar os produtos vendidos.",
                    "Erro na consulta",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new vendasVIEW().setVisible(true));
    }
}
