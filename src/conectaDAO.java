
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {

    private static String usuario;
    private static char[] senha;

    private boolean solicitarCredenciais() {
        JTextField campoUsuario = new JTextField("root");
        JPasswordField campoSenha = new JPasswordField();
        Object[] campos = {
            "Usuário do MySQL:", campoUsuario,
            "Senha do MySQL (pode ficar vazia):", campoSenha
        };

        int opcao = JOptionPane.showConfirmDialog(
                null,
                campos,
                "Conexão com o banco uc11",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (opcao != JOptionPane.OK_OPTION) {
            return false;
        }

        usuario = campoUsuario.getText().trim();
        senha = campoSenha.getPassword();
        return !usuario.isEmpty();
    }
    
    public Connection connectDB(){
        Connection conn = null;

        if (usuario == null && !solicitarCredenciais()) {
            return null;
        }

        try {

            String url = "jdbc:mysql://localhost:3306/uc11"
                    + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            conn = DriverManager.getConnection(url, usuario, new String(senha));

        } catch (SQLException erro){
            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível conectar ao banco uc11.\n"
                    + "Confira o usuário, a senha e se o MySQL está iniciado.\n\n"
                    + "Detalhes: " + erro.getMessage(),
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE);
            usuario = null;
            if (senha != null) {
                java.util.Arrays.fill(senha, '\0');
                senha = null;
            }
        }
        return conn;
    }
    
}
