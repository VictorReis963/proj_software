// fabrica de conexao com o sql server
class ConnectionFactory {

    private static final String URL =
        "jdbc:sqlserver://localhost:1433;"
      + "databaseName=SeuBanco;";
    private static final String USER = "sa";
    private static final String PASS = "suaSenha";

    public static Connection getConnection() {
        try {
            // carrega o driver jdbc do sql server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            throw new RuntimeException("jdbc driver nao encontrado. adicione o driver do sql server no classpath", e);
        }
    }
}
