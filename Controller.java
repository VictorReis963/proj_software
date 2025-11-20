import java.util.List;

// camada de controle que conversa com a tela
public class CadastroController {

    private CadastroService service = new CadastroService();

    // inicia cadastro recebendo dados da interface
    public boolean iniciarCadastro(
        PessoaFisica pessoa,
        String dadosPerfil,
        String email, String telefone,
        List<Habilidade> habilidades,
        List<Interesse> interesses
    ) {
        return service.cadastrarCandidatoCompleto(
            pessoa, dadosPerfil,
            email, telefone,
            habilidades, interesses
        );
    }
}
