public class RedeMaisSocial {
    public static void main(String[] args) {

        
        PessoaFisica pessoa = new PessoaFisica(
            "123.456.789-00",
            "José Bezerra",
            "1980-04-10",
            "Masculino",
            "Brasileiro",
            "Casado",
            "Analista de Sistemas"
        );

        PessoaFisicaDAO pessoaDAO = new PessoaFisicaDAO();
        pessoaDAO.inserir(pessoa);


        //  CRIAR PERFIL DO USUÁRIO
        // ===============================
        Perfil perfil = new Perfil("Perfil de José Bezerra com dados públicos e interesses sociais");
        PerfilDAO perfilDAO = new PerfilDAO();
        int idPerfil = perfilDAO.inserir(perfil); // recupera o ID gerado no banco


        //CADASTRAR CANDIDATO
        // ===============================
        Candidato candidato = new Candidato(
            "josebezerra@email.com",
            "11988887777",
            pessoa.getCpf(),
            idPerfil
        );

        CandidatoDAO candidatoDAO = new CandidatoDAO();
        candidatoDAO.inserir(candidato);


        // ADICIONAR HABILIDADES E INTERESSES
        // ===============================
        Habilidade habilidade1 = new Habilidade("Comunicação eficaz", "2025-11-12", "Ativa", idPerfil);
        Habilidade habilidade2 = new Habilidade("Liderança de equipes", "2025-11-12", "Ativa", idPerfil);

        HabilidadeDAO habilidadeDAO = new HabilidadeDAO();
        habilidadeDAO.inserir(habilidade1);
        habilidadeDAO.inserir(habilidade2);

        Interesse interesse1 = new Interesse("Projetos sociais", "2025-11-12", "Ativo", idPerfil);
        Interesse interesse2 = new Interesse("Inovação e tecnologia", "2025-11-12", "Ativo", idPerfil);

        InteresseDAO interesseDAO = new InteresseDAO();
        interesseDAO.inserir(interesse1);
        interesseDAO.inserir(interesse2);


        // SOLICITAR AFILIAÇÃO
        // ===============================
        Afiliacao afiliacao = new Afiliacao("2025-11-12", true, 1);
        AfiliacaoDAO afiliacaoDAO = new AfiliacaoDAO();
        afiliacaoDAO.inserir(afiliacao);


        //REGISTRAR CONSENTIMENTO
        // ===============================
        Consentimento consentimento = new Consentimento("2025-11-12", true, 1);
        ConsentimentoDAO consentimentoDAO = new ConsentimentoDAO();
        consentimentoDAO.inserir(consentimento);


        //ACEITAR OS TERMOS (ITEM TERMO)
        // ===============================
        ItemTermo item1 = new ItemTermo("Aceito compartilhar meus dados com a ONG", true, 1);
        ItemTermo item2 = new ItemTermo("Aceito receber comunicações por e-mail", true, 1);

        ItemTermoDAO itemTermoDAO = new ItemTermoDAO();
        itemTermoDAO.inserir(item1);
        itemTermoDAO.inserir(item2);


        Validacao validacao = new Validacao(
            "https://redemaisvida.com/validacao/123",
            true,
            "2025-11-12",
            1
        );

        ValidacaoDAO validacaoDAO = new ValidacaoDAO();
        validacaoDAO.inserir(validacao);


        //CONCLUSÃO DO FLUXO
        // ===============================
        System.out.println("\n🎉 Cadastro completo realizado com sucesso!");
        System.out.println("Fluxo: Pessoa -> Perfil -> Candidato -> Habilidades/Interesses -> Afiliacao -> Consentimento -> Termos -> Validacao");
    }
}
