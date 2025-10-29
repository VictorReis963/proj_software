
USE RedeMaisSocial;
GO


-- CASO 2: APROVA AFILIAÇÃO (SQL SERVER);
-- ==============================

-- REPRESENTANTE RMS (RESPONSAVEL POR APROVAR CANDIDATOS)
CREATE TABLE dbo.RepresentanteRMS (
    id_representante INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL
);
GO

-- ONG
CREATE TABLE dbo.Ong (
    id_ong INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    informacoes_contato VARCHAR(200),
    cnpj VARCHAR(18) NULL,  -- Pode se relacionar com PessoaJuridica
    CONSTRAINT FK_Ong_PessoaJuridica FOREIGN KEY (cnpj)
        REFERENCES dbo.PessoaJuridica(cnpj)
);
GO

-- CAMPANHA
CREATE TABLE dbo.Campanha (
    id_campanha INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_inicio DATE,
    data_fim DATE,
    id_ong INT NOT NULL,
    CONSTRAINT FK_Campanha_Ong FOREIGN KEY (id_ong)
        REFERENCES dbo.Ong(id_ong)
);
GO


-- ASSOCIAÇÃO ENTRE REPRESENTANTE RMS E AFILIAÇÃO
CREATE TABLE dbo.RepresentanteAfilia (
    id_representante INT NOT NULL,
    id_afiliacao INT NOT NULL,
    data_aprovacao DATE,
    aprovado BIT,
    CONSTRAINT PK_RepresentanteAfilia PRIMARY KEY (id_representante, id_afiliacao),
    CONSTRAINT FK_RepresentanteAfilia_Representante FOREIGN KEY (id_representante)
        REFERENCES dbo.RepresentanteRMS(id_representante),
    CONSTRAINT FK_RepresentanteAfilia_Afiliacao FOREIGN KEY (id_afiliacao)
        REFERENCES dbo.Afiliacao(id_afiliacao)
);
GO

-- HISTORICO DE OPERAÇÕES (PRINCIPALMENTE DO REPRESENTANTE
CREATE TABLE dbo.LogSistema (
    id_log INT IDENTITY(1,1) PRIMARY KEY,
    operacao VARCHAR(100),
    data_execucao DATETIME DEFAULT GETDATE(),
    id_representante INT NULL,
    CONSTRAINT FK_LogSistema_Representante FOREIGN KEY (id_representante)
        REFERENCES dbo.RepresentanteRMS(id_representante)
);
GO
