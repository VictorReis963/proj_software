import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// fabrica de conexao com sql server
public class ConnectionFactory {

    // url padrao sql server
    private static final String URL =
        "jdbc:sqlserver://localhost:1433;databaseName=RedeMaisSocial;encrypt=false;";

    // dados do usuario do banco
    private static final String USER = "sa";
    private static final String PASS = ""; // senha vazia

    // metodo que abre conexao com o banco
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("erro ao conectar ao sql server: " + e.getMessage());
        }
    }
}
