// arquivo unico contendo todas as classes DAO do sistema
// cada DAO acessa uma tabela do banco de dados
// obs: comentarios sem acentos e sem letras maiusculas

package dao;

import java.sql.*;
import model.*;

// classe responsavel por fornecer conexao com o banco mysql
class ConnectionFactory {

    // metodo que abre a conexao
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/cadastro_candidato",
                "root",
                "senha"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

// dao da tabela pessoa fisica
// insere dados basicos do usuario no banco
class PessoaFisicaDAO {

    public void inserir(PessoaFisica p) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO PessoaFisica VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.cpf);
            stmt.setString(2, p.nome);
            stmt.setInt(3, p.idade);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// dao da tabela perfil
// salva o perfil no banco e retorna o id gerado automaticamente
class PerfilDAO {

    public int inserir(Perfil p) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Perfil (dados) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.dados);
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}

// dao do candidato
// cadastra o candidato relacionando com pessoa fisica e perfil
class CandidatoDAO {

    public int inserir(Candidato c) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Candidato (email, telefone, cpf_pessoa, id_perfil) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.email);
            stmt.setString(2, c.telefone);
            stmt.setString(3, c.cpfPessoa);
            stmt.setInt(4, c.idPerfil);
            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}

// dao de habilidade
// usado no loop de cadastro de habilidades
class HabilidadeDAO {

    public void inserir(Habilidade h) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Habilidade (descricao, nivel, id_perfil) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, h.descricao);
            stmt.setString(2, h.nivel);
            stmt.setInt(3, h.idPerfil);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// dao de interesse
// usado no loop de cadastro de interesses
class InteresseDAO {

    public void inserir(Interesse i) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Interesse (descricao, id_perfil) VALUES (?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, i.descricao);
            stmt.setInt(2, i.idPerfil);
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// dao de afiliacao
// ultimo passo do cadastro do candidato
class AfiliacaoDAO {

    public void inserir(Afiliacao a) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Afiliacao (id_candidato) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, a.idCandidato);
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
