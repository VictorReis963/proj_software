import java.util.List;

// camada de servico que junta todas as operacoes de cadastro
public class CadastroService {

    // DAOs usados
    private PessoaFisicaDAO pessoaDAO = new PessoaFisicaDAO();
    private PerfilDAO perfilDAO = new PerfilDAO();
    private CandidatoDAO candidatoDAO = new CandidatoDAO();
    private HabilidadeDAO habilidadeDAO = new HabilidadeDAO();
    private InteresseDAO interesseDAO = new InteresseDAO();
    private AfiliacaoDAO afiliacaoDAO = new AfiliacaoDAO();

    // executa fluxo completo do caso 1
    public boolean cadastrarCandidatoCompleto(
        PessoaFisica pessoa,
        String dadosPerfil,
        String email, String telefone,
        List<Habilidade> habilidades,
        List<Interesse> interesses
    ) {
        try {
            // salva pessoa
            pessoaDAO.inserir(pessoa);

            // cria perfil
            int idPerfil = perfilDAO.inserir(new Perfil(dadosPerfil));

            // cria candidato
            int idCandidato = candidatoDAO.inserir(
                new Candidato(email, telefone, pessoa.cpf, idPerfil)
            );

            // salva habilidades
            for (Habilidade h : habilidades) {
                h.idPerfil = idPerfil;
                habilidadeDAO.inserir(h);
            }

            // salva interesses
            for (Interesse i : interesses) {
                i.idPerfil = idPerfil;
                interesseDAO.inserir(i);
            }

            // cria afiliacao
            afiliacaoDAO.inserir(new Afiliacao("2025-11-20", true, idCandidato));

            return true;

        } catch (Exception e) {
            System.out.println("erro no service: " + e.getMessage());
            return false;
        }
    }
}
