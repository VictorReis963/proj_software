// arquivo unico contendo connection factory e todos os daos
package proj.app;

import java.sql.*;

// classe publica que fornece conexao com o sql server
class ConnectionFactory {

    // altere usuario e senha conforme seu ambiente
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=cadastro_candidato";
    private static final String USER = "sa";
    private static final String PASS = "sua_senha_aqui";

    // carrega o driver opcionalmente, e retorna conexao
    public static Connection getConnection() throws SQLException {
        try {
            // opcional: essa linha pode ser removida se o driver for gerenciado pelo classpath
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            // se o driver nao estiver no classpath, lancamos runtime explicito
            throw new RuntimeException("jdbc driver nao encontrado. adicione o driver do sql server no classpath", e);
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

// dao de pessoa fisica (package-private)
class PessoaFisicaDAO {

    // insere uma pessoa fisica no banco
    public void inserir(PessoaFisica p) {
        String sql = "INSERT INTO PessoaFisica (cpf, nome, idade) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.cpf);
            stmt.setString(2, p.nome);
            stmt.setInt(3, p.idade);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

// dao de perfil (package-private)
class PerfilDAO {

    // insere perfil e retorna id gerado
    public int inserir(Perfil p) {
        String sql = "INSERT INTO Perfil (dados) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.dados);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}

// dao de candidato (package-private)
class CandidatoDAO {

    public int inserir(Candidato c) {
        String sql = "INSERT INTO Candidato (email, telefone, cpf_pessoa, id_perfil) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.email);
            stmt.setString(2, c.telefone);
            stmt.setString(3, c.cpfPessoa);
            stmt.setInt(4, c.idPerfil);
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
}

// dao de habilidade (package-private)
class HabilidadeDAO {

    public void inserir(Habilidade h) {
        String sql = "INSERT INTO Habilidade (descricao, nivel, id_perfil) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, h.descricao);
            stmt.setString(2, h.nivel);
            stmt.setInt(3, h.idPerfil);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

// dao de interesse (package-private)
class InteresseDAO {

    public void inserir(Interesse i) {
        String sql = "INSERT INTO Interesse (descricao, id_perfil) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, i.descricao);
            stmt.setInt(2, i.idPerfil);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

// dao de afiliacao (package-private)
class AfiliacaoDAO {

    public void inserir(Afiliacao a) {
        String sql = "INSERT INTO Afiliacao (id_candidato, data_registro) VALUES (?, GETDATE())";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, a.idCandidato);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
