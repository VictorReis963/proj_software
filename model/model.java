// arquivo unico contendo todas as classes de modelo do sistema
// obs: comentarios sem acentos e sem letras maiusculas conforme solicitado

package model;

// classe que representa uma pessoa fisica no sistema
// esse objeto guarda dados pessoais basicos
public class PessoaFisica {
    public String cpf;
    public String nome;
    public int idade;

    // construtor recebe os dados da pessoa fisica
    public PessoaFisica(String cpf, String nome, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }
}

// classe que representa o perfil do candidato
// o perfil guarda informacoes textuais adicionais
class Perfil {
    public int id;
    public String dados;

    public Perfil(String dados) {
        this.dados = dados;
    }
}

// classe que representa o candidato completo
// ele referencia a tabela pessoa fisica e o perfil
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

// classe que representa uma habilidade do candidato
// cada habilidade pertence a um perfil
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

// classe que representa um interesse do candidato
// cada interesse pertence ao perfil
class Interesse {
    public int id;
    public String descricao;
    public int idPerfil;

    public Interesse(String descricao, int idPerfil) {
        this.descricao = descricao;
        this.idPerfil = idPerfil;
    }
}

// classe que representa a afiliacao do candidato
// usada na etapa final do cadastro
class Afiliacao {
    public int id;
    public int idCandidato;

    public Afiliacao(int idCandidato) {
        this.idCandidato = idCandidato;
    }
}
