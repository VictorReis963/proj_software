import java.sql.*;

// ======================================================================
/*é um padrão de projeto que separa as regras de negócio da lógica de 
acesso a dados, como consultas a um banco de dados. Ele funciona como uma 
interface genérica para manipular um tipo de dado específico, permitindo 
que a aplicação acesse o banco de dados sem precisar saber como a 
comunicação é feita, tornando o código mais organizado, legível e fácil
de manter. 
*/
// cada classe salva um tipo diferente no sql server
// ======================================================================

// salva pessoa fisica no banco
class PessoaFisicaDAO {
    public void inserir(PessoaFisica p) throws SQLException {

        String sql = "INSERT INTO PessoaFisica VALUES (?, ?, ?, ?, ?, ?, ?)";

        // abre conexao e prepara sql
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {

            // envia valores para o banco
            s.setString(1, p.cpf);
            s.setString(2, p.nome);
            s.setString(3, p.nascimento);
            s.setString(4, p.sexo);
            s.setString(5, p.nacionalidade);
            s.setString(6, p.estadoCivil);
            s.setString(7, p.profissao);

            s.executeUpdate(); // grava no banco
        }
    }
}

// cria perfil e retorna id gerado
class PerfilDAO {
    public int inserir(Perfil p) throws SQLException {

        String sql = "INSERT INTO Perfil (dados) VALUES (?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            s.setString(1, p.dados);
            s.executeUpdate();

            // pega id criado automaticamente
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

            return -1;
        }
    }
}

// salva candidato e retorna id
class CandidatoDAO {
    public int inserir(Candidato c) throws SQLException {

        String sql = "INSERT INTO Candidato (email, telefone, cpf, id_perfil) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.email);
            stmt.setString(2, c.telefone);
            stmt.setString(3, c.cpf);
            stmt.setInt(4, c.idPerfil);

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

            return -1;
        }
    }
}

// salva habilidade no banco
class HabilidadeDAO {
    public void inserir(Habilidade h) throws SQLException {

        String sql = "INSERT INTO Habilidade (data, status, descricao, id_perfil) VALUES (?, ?, ?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {

            s.setString(1, h.data);
            s.setString(2, h.status);
            s.setString(3, h.descricao);
            s.setInt(4, h.idPerfil);

            s.executeUpdate();
        }
    }
}

// salva interesse
class InteresseDAO {
    public void inserir(Interesse i) throws SQLException {

        String sql = "INSERT INTO Interesse (data, status, descricao, id_perfil) VALUES (?, ?, ?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {

            s.setString(1, i.data);
            s.setString(2, i.status);
            s.setString(3, i.descricao);
            s.setInt(4, i.idPerfil);

            s.executeUpdate();
        }
    }
}

// salva afiliacao para o candidato
class AfiliacaoDAO {
    public void inserir(Afiliacao a) throws SQLException {

        String sql = "INSERT INTO Afiliacao (data_solicitacao, status, id_candidato) VALUES (?, ?, ?)";

        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {

            s.setString(1, a.dataSolicitacao);
            s.setBoolean(2, a.status);
            s.setInt(3, a.idCandidato);

            s.executeUpdate();
        }
    }
}
