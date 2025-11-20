// tela que simula a interface do usuario
// aqui pedimos dados e enviamos para o controller
// tudo em modo texto, simples e direto

package ui;

import controller.CadastroController;
import model.*;
import java.util.*;

public class TelaCadastro {

    private CadastroController controller = new CadastroController();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {

        System.out.println("-- cadastro de candidato --");

        // dados pessoa fisica
        System.out.print("cpf: ");
        String cpf = sc.nextLine();

        System.out.print("nome: ");
        String nome = sc.nextLine();

        System.out.print("idade: ");
        int idade = Integer.parseInt(sc.nextLine());

        PessoaFisica pessoa = new PessoaFisica(cpf, nome, idade);

        // dados do perfil
        System.out.print("informacoes do perfil: ");
        String dadosPerfil = sc.nextLine();

        // dados candidato
        System.out.print("email: ");
        String email = sc.nextLine();

        System.out.print("telefone: ");
        String telefone = sc.nextLine();

        // habilidades
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

        // interesses
        List<Interesse> interesses = new ArrayList<>();
        System.out.print("quantos interesses deseja cadastrar? ");
        int qtdInt = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < qtdInt; i++) {
            System.out.print("descricao interesse: ");
            String desc = sc.nextLine();

            interesses.add(new Interesse(desc, 0));
        }

        // envia tudo para o controller
        boolean sucesso = controller.enviarDados(
            pessoa,
            dadosPerfil,
            email,
            telefone,
            habilidades,
            interesses
        );

        if (sucesso)
            System.out.println("cadastro concluido com sucesso!");
        else
            System.out.println("erro ao realizar cadastro.");
    }
}
