// models do caso 1
// =====================================================================

// representa pessoa fisica no banco
public class PessoaFisica {
    public String cpf, nome, nascimento, sexo, nacionalidade, estadoCivil, profissao;

    // construtor recebe todos os dados da pessoa
    public PessoaFisica(String cpf, String nome, String nascimento, String sexo,
                        String nacionalidade, String estadoCivil, String profissao) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.sexo = sexo;
        this.nacionalidade = nacionalidade;
        this.estadoCivil = estadoCivil;
        this.profissao = profissao;
    }
}

// representa o perfil do usuario
public class Perfil {
    public int id;
    public String dados;

    // armazena texto do perfil, id e gerado no banco
    public Perfil(String dados) {
        this.dados = dados;
    }
}

// representa candidato vinculado a pessoa e perfil
public class Candidato {
    public String email, telefone, cpf;
    public int idPerfil;

    // amarra email, telefone e chave do perfil
    public Candidato(String email, String telefone, String cpf, int idPerfil) {
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.idPerfil = idPerfil;
    }
}

// representa uma habilidade do perfil
public class Habilidade {
    public String descricao, data, status;
    public int idPerfil;

    public Habilidade(String descricao, String data, String status, int idPerfil) {
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.idPerfil = idPerfil;
    }
}

// representa interesse do perfil
public class Interesse {
    public String descricao, data, status;
    public int idPerfil;

    public Interesse(String descricao, String data, String status, int idPerfil) {
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.idPerfil = idPerfil;
    }
}

// representa solicitacao de afiliacao
public class Afiliacao {
    public String dataSolicitacao;
    public boolean status;
    public int idCandidato;

    // vincula afiliacao ao candidato especifico
    public Afiliacao(String dataSolicitacao, boolean status, int idCandidato) {
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
        this.idCandidato = idCandidato;
    }
}
