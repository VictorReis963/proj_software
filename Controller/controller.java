// controller faz a ponte entre interface grafica e servico
// aqui nao tem logica pesada, so encaminhamento

package controller;

import model.*;
import service.CadastroService;
import java.util.List;

public class CadastroController {

    private CadastroService service = new CadastroService();

    // metodo chamado pela tela de cadastro
    public boolean enviarDados(
        PessoaFisica pessoa,
        String dadosPerfil,
        String email,
        String telefone,
        List<Habilidade> habilidades,
        List<Interesse> interesses
    ) {
        return service.cadastrarCandidatoCompleto(
            pessoa,
            dadosPerfil,
            email,
            telefone,
            habilidades,
            interesses
        );
    }
}
