// tela em modo texto (console) que coleta dados do usuario
// comentarios sem acentos e sem letras maiusculas
package proj.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TelaCadastro {

    private CadastroController controller = new CadastroController();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {

        System.out.println("-- cadastro de candidato --");

        System.out.print("cpf: ");
        String cpf = sc.nextLine();

        System.out.print("nome: ");
        String nome = sc.nextLine();

        System.out.print("idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        PessoaFisica pessoa = new PessoaFisica(cpf, nome, idade);

        System.out.print("informacoes do perfil: ");
        String dadosPerfil = sc.nextLine();

        System.out.print("email: ");
        String email = sc.nextLine();

        System.out.print("telefone: ");
        String telefone = sc.nextLine();

        List<Habilidade> habilidades = new ArrayList<>();
        System.out.print("quantas habilidades deseja cadastrar? ");
        int qtdHab = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < qtdHab; i++) {
            System.out.print("descricao habilidade: ");
            String desc = sc.nextLine();
            System.out.print("nivel habilidade: ");
            String nivel = sc.nextLine();
            habilidades.add(new Habilidade(desc, nivel, 0));
        }

        List<Interesse> interesses = new ArrayList<>();
        System.out.print("quantos interesses deseja cadastrar? ");
        int qtdInt = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < qtdInt; i++) {
            System.out.print("descricao interesse: ");
            String desc = sc.nextLine();
            interesses.add(new Interesse(desc, 0));
        }

        boolean sucesso = controller.enviarDados(
                pessoa, dadosPerfil, email, telefone, habilidades, interesses
        );

        if (sucesso) System.out.println("cadastro concluido com sucesso!");
        else System.out.println("erro ao realizar cadastro.");
    }
}
