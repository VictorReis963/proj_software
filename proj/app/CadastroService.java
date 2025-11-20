// servico que implementa o fluxo do diagrama
package proj.app;

import java.util.List;

public class CadastroService {

    // daos usados (package-private classes do arquivo daos)
    private PessoaFisicaDAO pessoaDAO = new PessoaFisicaDAO();
    private PerfilDAO perfilDAO = new PerfilDAO();
    private CandidatoDAO candidatoDAO = new CandidatoDAO();
    private HabilidadeDAO habilidadeDAO = new HabilidadeDAO();
    private InteresseDAO interesseDAO = new InteresseDAO();
    private AfiliacaoDAO afiliacaoDAO = new AfiliacaoDAO();

    // metodo principal que faz todo o fluxo de cadastro
    public boolean cadastrarCandidatoCompleto(
            PessoaFisica pessoa,
            String dadosPerfil,
            String email, String telefone,
            List<Habilidade> habilidades,
            List<Interesse> interesses) {

        try {
            // salva pessoa
            pessoaDAO.inserir(pessoa);

            // cria perfil e pega id
            int idPerfil = perfilDAO.inserir(new Perfil(dadosPerfil));

            // cria candidato e pega id
            int idCandidato = candidatoDAO.inserir(
                    new Candidato(email, telefone, pessoa.cpf, idPerfil)
            );

            // insere habilidades
            for (Habilidade h : habilidades) {
                h.idPerfil = idPerfil;
                habilidadeDAO.inserir(h);
            }

            // insere interesses
            for (Interesse i : interesses) {
                i.idPerfil = idPerfil;
                interesseDAO.inserir(i);
            }

            // cria afiliacao
            afiliacaoDAO.inserir(new Afiliacao(idCandidato));
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}