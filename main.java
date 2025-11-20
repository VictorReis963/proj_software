import java.util.ArrayList;

// classe principal usada para testar o cadastro
public class Main {
    public static void main(String[] args) {

        // cria controlador
        CadastroController controller = new CadastroController();

        // cria pessoa fisica
        PessoaFisica p = new PessoaFisica(
                "12345678900",
                "joao",
                "2000-01-01",
                "m",
                "brasileiro",
                "solteiro",
                "estudante"
        );

        // dados do perfil
        String dadosPerfil = "perfil do candidato";

        // contatos
        String email = "joao@email.com";
        String telefone = "11999999999";

        // lista de habilidades
        ArrayList<Habilidade> habilidades = new ArrayList<>();
        habilidades.add(new Habilidade("java", "2025-01-01", "ativo", 0));

        // lista de interesses
        ArrayList<Interesse> interesses = new ArrayList<>();
        interesses.add(new Interesse("programacao", "2025-01-01", "ativo", 0));

        // executa cadastro
        boolean ok = controller.iniciarCadastro(
                p, dadosPerfil,
                email, telefone,
                habilidades, interesses
        );

        if (ok) System.out.println("cadastro concluido");
        else System.out.println("falha no cadastro");
    }
}
