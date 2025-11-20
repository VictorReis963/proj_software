// classe responsavel por executar o fluxo completo do cadastro
// aqui acontece exatamente o que esta no diagrama de sequencia

package service;

import dao.*;
import model.*;
import java.util.List;

public class CadastroService {

    // daos usados pelo servico
    private PessoaFisicaDAO pessoaDAO = new PessoaFisicaDAO();
    private PerfilDAO perfilDAO = new PerfilDAO();
    private CandidatoDAO candidatoDAO = new CandidatoDAO();
    private HabilidadeDAO habilidadeDAO = new HabilidadeDAO();
    private InteresseDAO interesseDAO = new InteresseDAO();
    private AfiliacaoDAO afiliacaoDAO = new AfiliacaoDAO();

    // metodo principal do cadastro
    public boolean cadastrarCandidatoCompleto(
        PessoaFisica pessoa,
        String dadosPerfil,
        String email,
        String telefone,
        List<Habilidade> habilidades,
        List<Interesse> interesses
    ) {
        try {

            // etapa 1: salva pessoa
            pessoaDAO.inserir(pessoa);

            // etapa 1: salva perfil
            int idPerfil = perfilDAO.inserir(new Perfil(dadosPerfil));

            // etapa 1: salva candidato
            int idCandidato = candidatoDAO.inserir(
                new Candidato(email, telefone, pessoa.cpf, idPerfil)
            );

            // etapa 2: loop de habilidades
            for (Habilidade h : habilidades) {
                h.idPerfil = idPerfil;
                habilidadeDAO.inserir(h);
            }

            // etapa 3: loop de interesses
            for (Interesse i : interesses) {
                i.idPerfil = idPerfil;
                interesseDAO.inserir(i);
            }

            // etapa 4: cria afiliacao
            afiliacaoDAO.inserir(new Afiliacao(idCandidato));

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
