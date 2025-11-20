-- script de criacao para microsoft sql server
CREATE DATABASE cadastro_candidato;
GO
USE cadastro_candidato;
GO

CREATE TABLE PessoaFisica (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NULL
);
GO

CREATE TABLE Perfil (
    id INT IDENTITY(1,1) PRIMARY KEY,
    dados NVARCHAR(MAX) NULL
);
GO

CREATE TABLE Candidato (
    id INT IDENTITY(1,1) PRIMARY KEY,
    email VARCHAR(100) NULL,
    telefone VARCHAR(20) NULL,
    cpf_pessoa VARCHAR(14) NULL,
    id_perfil INT NULL,
    CONSTRAINT fk_candidato_pessoa FOREIGN KEY (cpf_pessoa) REFERENCES PessoaFisica(cpf),
    CONSTRAINT fk_candidato_perfil FOREIGN KEY (id_perfil) REFERENCES Perfil(id)
);
GO

CREATE TABLE Habilidade (
    id INT IDENTITY(1,1) PRIMARY KEY,
    descricao VARCHAR(100) NULL,
    nivel VARCHAR(50) NULL,
    id_perfil INT NULL,
    CONSTRAINT fk_habilidade_perfil FOREIGN KEY (id_perfil) REFERENCES Perfil(id)
);
GO

CREATE TABLE Interesse (
    id INT IDENTITY(1,1) PRIMARY KEY,
    descricao VARCHAR(100) NULL,
    id_perfil INT NULL,
    CONSTRAINT fk_interesse_perfil FOREIGN KEY (id_perfil) REFERENCES Perfil(id)
);
GO

CREATE TABLE Afiliacao (
    id INT IDENTITY(1,1) PRIMARY KEY,
    id_candidato INT NULL,
    data_registro DATETIME DEFAULT GETDATE(),
    CONSTRAINT fk_afiliacao_candidato FOREIGN KEY (id_candidato) REFERENCES Candidato(id)
);
GO