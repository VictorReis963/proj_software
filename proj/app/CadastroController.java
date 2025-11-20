// controller que faz a ponte entre a interface e o servico
package proj.app;

import java.util.List;

public class CadastroController {

    private CadastroService service = new CadastroService();

    public boolean enviarDados(
            PessoaFisica pessoa,
            String dadosPerfil,
            String email, String telefone,
            List<Habilidade> habilidades,
            List<Interesse> interesses) {

        return service.cadastrarCandidatoCompleto(
                pessoa, dadosPerfil, email, telefone, habilidades, interesses
        );
    }
}
