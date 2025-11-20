// arquivo unico com as entidades do dominio
package proj.app;

// classe publica que representa pessoa fisica
 class PessoaFisica {
    public String cpf;
    public String nome;
    public int idade;

    public PessoaFisica(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }
}

// classe perfil (package-private)
class Perfil {
    public int id;
    public String dados;

    public Perfil(String dados) {
        this.dados = dados;
    }
}

// classe candidato (package-private)
class Candidato {
    public int id;
    public String email;
    public String telefone;
    public String cpfPessoa;
    public int idPerfil;

    public Candidato(String email, String telefone, String cpfPessoa, int idPerfil) {
        this.email = email;
        this.telefone = telefone;
        this.cpfPessoa = cpfPessoa;
        this.idPerfil = idPerfil;
    }
}

// classe habilidade (package-private)
class Habilidade {
    public int id;
    public String descricao;
    public String nivel;
    public int idPerfil;

    public Habilidade(String descricao, String nivel, int idPerfil) {
        this.descricao = descricao;
        this.nivel = nivel;
        this.idPerfil = idPerfil;
    }
}

// classe interesse (package-private)
class Interesse {
    public int id;
    public String descricao;
    public int idPerfil;

    public Interesse(String descricao, int idPerfil) {
        this.descricao = descricao;
        this.idPerfil = idPerfil;
    }
}

// classe afiliacao (package-private)
class Afiliacao {
    public int id;
    public int idCandidato;

    public Afiliacao(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}