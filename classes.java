import java.sql.*;

class ConnectionFactory {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=RedeMaisSocial;encrypt=false";
    private static final String USER = "sa"; // usuário do SQL Server
    private static final String PASSWORD = "sua_senha_aqui"; 

    public static Connection getConnection() { //CRIA CONEXÃO COM O BANCO JDBC
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) { // ENCAPSULAMENTO DE ERRO
            throw new RuntimeException("Erro ao conectar ao banco: " + e.getMessage(), e);
        }
    }
}

// CLASSES DE MODELO
// ============================================
class PessoaFisica {
    private String cpf, nome, dataNascimento, sexo, nacionalidade, estadoCivil, profissao;
    //CONSTRUTOR, INCIA OBJETO COM OS DADOS
    public PessoaFisica(String cpf, String nome, String dataNascimento, String sexo,
                        String nacionalidade, String estadoCivil, String profissao) {
        this.cpf = cpf; // ATRIBUTOS
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getDataNascimento() { return dataNascimento; }
    public String getSexo() { return sexo; }
    public String getNacionalidade() { return nacionalidade; }
    public String getEstadoCivil() { return estadoCivil; }
    public String getProfissao() { return profissao; }
}

class Perfil {
    private int idPerfil;
    private String dados;

    public Perfil(String dados) { this.dados = dados; }
    public int getIdPerfil() { return idPerfil; }
    public String getDados() { return dados; }
}

class Candidato {
    private String email, telefone, cpf;
    private int idPerfil;

    public Candidato(String email, String telefone, String cpf, int idPerfil) {
        this.email = email; this.telefone = telefone; this.cpf = cpf; this.idPerfil = idPerfil;
    }

    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    public int getIdPerfil() { return idPerfil; }
}

class Habilidade {
    private String descricao, data, status;
    private int idPerfil;

    public Habilidade(String descricao, String data, String status, int idPerfil) {
        this.descricao = descricao; this.data = data; this.status = status; this.idPerfil = idPerfil;
    }

    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getStatus() { return status; }
    public int getIdPerfil() { return idPerfil; }
}

class Interesse {
    private String descricao, data, status;
    private int idPerfil;

    public Interesse(String descricao, String data, String status, int idPerfil) {
        this.descricao = descricao; this.data = data; this.status = status; this.idPerfil = idPerfil;
    }

    public String getDescricao() { return descricao; }
    public String getData() { return data; }
    public String getStatus() { return status; }
    public int getIdPerfil() { return idPerfil; }
}

class Afiliacao {
    private String dataSolicitacao;
    private boolean status;
    private int idCandidato;

    public Afiliacao(String dataSolicitacao, boolean status, int idCandidato) {
        this.dataSolicitacao = dataSolicitacao; this.status = status; this.idCandidato = idCandidato;
    }

    public String getDataSolicitacao() { return dataSolicitacao; }
    public boolean isStatus() { return status; }
    public int getIdCandidato() { return idCandidato; }
}

class Consentimento {
    private String dataAceite;
    private boolean status;
    private int idAfiliacao;

    public Consentimento(String dataAceite, boolean status, int idAfiliacao) {
        this.dataAceite = dataAceite; this.status = status; this.idAfiliacao = idAfiliacao;
    }

    public String getDataAceite() { return dataAceite; }
    public boolean isStatus() { return status; }
    public int getIdAfiliacao() { return idAfiliacao; }
}

class ItemTermo {
    private String descricao;
    private boolean aceite;
    private int idConsentimento;

    public ItemTermo(String descricao, boolean aceite, int idConsentimento) {
        this.descricao = descricao; this.aceite = aceite; this.idConsentimento = idConsentimento;
    }

    public String getDescricao() { return descricao; }
    public boolean isAceite() { return aceite; }
    public int getIdConsentimento() { return idConsentimento; }
}

class Validacao {
    private String link, dataValidacao;
    private boolean status;
    private int idAfiliacao;

    public Validacao(String link, boolean status, String dataValidacao, int idAfiliacao) {
        this.link = link; this.status = status; this.dataValidacao = dataValidacao; this.idAfiliacao = idAfiliacao;
    }

    public String getLink() { return link; }
    public boolean isStatus() { return status; }
    public String getDataValidacao() { return dataValidacao; }
    public int getIdAfiliacao() { return idAfiliacao; }
}

// CLASSES DAO
// ============================================
class PessoaFisicaDAO {
    public void inserir(PessoaFisica p) {
        String sql = "INSERT INTO PessoaFisica (cpf, nome, data_nascimento, sexo, nacionalidade, estado_civil, profissao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, p.getCpf());
            s.setString(2, p.getNome());
            s.setString(3, p.getDataNascimento());
            s.setString(4, p.getSexo());
            s.setString(5, p.getNacionalidade());
            s.setString(6, p.getEstadoCivil());
            s.setString(7, p.getProfissao());
            s.executeUpdate();
            System.out.println(" Pessoa Física inserida!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class PerfilDAO {
    public int inserir(Perfil p) {
        String sql = "INSERT INTO Perfil (dados) VALUES (?)";
        int idGerado = -1;
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            s.setString(1, p.getDados());
            s.executeUpdate();
            ResultSet rs = s.getGeneratedKeys();
            if (rs.next()) idGerado = rs.getInt(1);
            System.out.println(" Perfil criado ID: " + idGerado);
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
        return idGerado;
    }
}

class CandidatoDAO {
    public void inserir(Candidato c) {
        String sql = "INSERT INTO Candidato (email, telefone, cpf, id_perfil) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getEmail());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getCpf());
            stmt.setInt(4, c.getIdPerfil());
            stmt.executeUpdate();
            System.out.println(" Candidato inserido!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class HabilidadeDAO {
    public void inserir(Habilidade h) {
        String sql = "INSERT INTO Habilidade (data, status, descricao, id_perfil) VALUES (?, ?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, h.getData());
            s.setString(2, h.getStatus());
            s.setString(3, h.getDescricao());
            s.setInt(4, h.getIdPerfil());
            s.executeUpdate();
            System.out.println(" Habilidade adicionada!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class InteresseDAO {
    public void inserir(Interesse i) {
        String sql = "INSERT INTO Interesse (data, status, descricao, id_perfil) VALUES (?, ?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, i.getData());
            s.setString(2, i.getStatus());
            s.setString(3, i.getDescricao());
            s.setInt(4, i.getIdPerfil());
            s.executeUpdate();
            System.out.println(" Interesse adicionado!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class AfiliacaoDAO {
    public void inserir(Afiliacao a) {
        String sql = "INSERT INTO Afiliacao (data_solicitacao, status, id_candidato) VALUES (?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, a.getDataSolicitacao());
            s.setBoolean(2, a.isStatus());
            s.setInt(3, a.getIdCandidato());
            s.executeUpdate();
            System.out.println(" Afiliacao criada!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class ConsentimentoDAO {
    public void inserir(Consentimento c) {
        String sql = "INSERT INTO Consentimento (data_aceite, status, id_afiliacao) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getDataAceite());
            stmt.setBoolean(2, c.isStatus());
            stmt.setInt(3, c.getIdAfiliacao());
            stmt.executeUpdate();
            System.out.println(" Consentimento salvo!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class ItemTermoDAO {
    public void inserir(ItemTermo i) {
        String sql = "INSERT INTO ItemTermo (descricao, aceite, id_consentimento) VALUES (?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, i.getDescricao());
            s.setBoolean(2, i.isAceite());
            s.setInt(3, i.getIdConsentimento());
            s.executeUpdate();
            System.out.println(" Item termo salvo!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}

class ValidacaoDAO {
    public void inserir(Validacao v) {
        String sql = "INSERT INTO Validacao (link, status, data_validacao, id_afiliacao) VALUES (?, ?, ?, ?)";
        try (Connection c = ConnectionFactory.getConnection();
             PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, v.getLink());
            s.setBoolean(2, v.isStatus());
            s.setString(3, v.getDataValidacao());
            s.setInt(4, v.getIdAfiliacao());
            s.executeUpdate();
            System.out.println(" Validação criada!");
        } catch (SQLException e) { System.out.println(" Erro: " + e.getMessage()); }
    }
}


