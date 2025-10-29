--usado no sql server
CREATE DATABASE RedeMaisSocial;
GO

USE RedeMaisSocial;
GO

-- ENTIDADE
CREATE TABLE dbo.Entidade (
    id_entidade INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);
GO

-- LOCALIZACAO
CREATE TABLE dbo.Localizacao (
    id_localizacao INT IDENTITY(1,1) PRIMARY KEY,
    tipoGeo VARCHAR(50),-- (tipo de residencia)
    logradouro VARCHAR(100),
    numero VARCHAR(20),
    complemento VARCHAR(50),
    codigoPostal VARCHAR(20),
    cidade VARCHAR(50),
    estado VARCHAR(50),
    pais VARCHAR(50),
    id_entidade INT NOT NULL,
    CONSTRAINT FK_Localizacao_Entidade FOREIGN KEY (id_entidade)
        REFERENCES dbo.Entidade(id_entidade)
);
GO

-- PESSOA FISICA
CREATE TABLE dbo.PessoaFisica (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(100),
    data_nascimento DATE,
    sexo VARCHAR(15),
    nacionalidade VARCHAR(50),
    estado_civil VARCHAR(30),
    profissao VARCHAR(50)
);
GO

-- FORMACAO
CREATE TABLE dbo.Formacao (
    id_formacao INT IDENTITY(1,1) PRIMARY KEY,
    descricao VARCHAR(200),
    data_conclusao DATE,
    cpf VARCHAR(14) NOT NULL,
    CONSTRAINT FK_Formacao_PessoaFisica FOREIGN KEY (cpf)
        REFERENCES dbo.PessoaFisica(cpf)
);
GO

-- PESSOA JURIDICA
CREATE TABLE dbo.PessoaJuridica (
    cnpj VARCHAR(18) PRIMARY KEY,
    nome_legal VARCHAR(100),
    nome_organizacional VARCHAR(100),
    estado_registro VARCHAR(50),
    registro_companhia VARCHAR(50),
    cpf_representante VARCHAR(14) NULL,
    CONSTRAINT FK_PessoaJuridica_PessoaFisica FOREIGN KEY (cpf_representante)
        REFERENCES dbo.PessoaFisica(cpf)
);
GO

-- CERTIDAO NEGATIVA
CREATE TABLE dbo.CertidaoNegativa (
    id_certidao INT IDENTITY(1,1) PRIMARY KEY,
    estado BIT,
    cpf VARCHAR(14) NOT NULL,
    CONSTRAINT FK_Certidao_PessoaFisica FOREIGN KEY (cpf)
        REFERENCES dbo.PessoaFisica(cpf)
);
GO

-- PERFIL
CREATE TABLE dbo.Perfil (
    id_perfil INT IDENTITY(1,1) PRIMARY KEY,
    dados TEXT NULL
);
GO

-- HABILIDADE
CREATE TABLE dbo.Habilidade (
    id_habilidade INT IDENTITY(1,1) PRIMARY KEY,
    data DATE,
    status VARCHAR(30),
    descricao TEXT,
    id_perfil INT NOT NULL,
    CONSTRAINT FK_Habilidade_Perfil FOREIGN KEY (id_perfil)
        REFERENCES dbo.Perfil(id_perfil)
);
GO

-- INTERESSE
CREATE TABLE dbo.Interesse (
    id_interesse INT IDENTITY(1,1) PRIMARY KEY,
    data DATE,
    status VARCHAR(30),
    descricao TEXT,
    id_perfil INT NOT NULL,
    CONSTRAINT FK_Interesse_Perfil FOREIGN KEY (id_perfil)
        REFERENCES dbo.Perfil(id_perfil)
);
GO

-- CANDIDATO
CREATE TABLE dbo.Candidato (
    id_candidato INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(100),
    telefone VARCHAR(20),
    cpf VARCHAR(14) NULL,
    id_perfil INT NULL,
    CONSTRAINT FK_Candidato_PessoaFisica FOREIGN KEY (cpf)
        REFERENCES dbo.PessoaFisica(cpf),
    CONSTRAINT FK_Candidato_Perfil FOREIGN KEY (id_perfil)
        REFERENCES dbo.Perfil(id_perfil)
);
GO

-- AFILIACAO
CREATE TABLE dbo.Afiliacao (
    id_afiliacao INT IDENTITY(1,1) PRIMARY KEY,
    data_solicitacao DATE,
    status BIT,
    id_candidato INT NOT NULL,
    CONSTRAINT FK_Afiliacao_Candidato FOREIGN KEY (id_candidato)
        REFERENCES dbo.Candidato(id_candidato)
);
GO

-- VALIDACAO
CREATE TABLE dbo.Validacao (
    id_validacao INT IDENTITY(1,1) PRIMARY KEY,
    link VARCHAR(250),
    status BIT,
    data_validacao DATE,
    id_afiliacao INT NULL,
    CONSTRAINT FK_Validacao_Afiliacao FOREIGN KEY (id_afiliacao)
        REFERENCES dbo.Afiliacao(id_afiliacao)
);
GO

-- CONSENTIMENTO
CREATE TABLE dbo.Consentimento (
    id_consentimento INT IDENTITY(1,1) PRIMARY KEY,
    data_aceite DATE,
    status BIT,
    id_afiliacao INT NOT NULL UNIQUE,
    CONSTRAINT FK_Consentimento_Afiliacao FOREIGN KEY (id_afiliacao)
        REFERENCES dbo.Afiliacao(id_afiliacao)
);
GO

-- ITEM TERMO
CREATE TABLE dbo.ItemTermo (
    id_item INT IDENTITY(1,1) PRIMARY KEY,
    descricao TEXT,
    aceite BIT,
    id_consentimento INT NOT NULL,
    CONSTRAINT FK_ItemTermo_Consentimento FOREIGN KEY (id_consentimento)
        REFERENCES dbo.Consentimento(id_consentimento)
);
GO

-- VIRTUAL (contato online)
CREATE TABLE dbo.Virtual (
    id_virtual INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(100),
    telefone VARCHAR(20),
    id_localizacao INT NULL,
    CONSTRAINT FK_Virtual_Localizacao FOREIGN KEY (id_localizacao)
        REFERENCES dbo.Localizacao(id_localizacao)
);
GO
