
    //criando as tabelas do banco de dados

CREATE TABLE IF NOT EXISTS usuarios (
    id VARCHAR(250) PRIMARY KEY,
    nome VARCHAR(250),
    email VARCHAR(250),
    telefone VARCHAR(250),
    endereco VARCHAR(250)
);

CREATE TABLE IF NOT EXISTS livros (
    isbn VARCHAR(250) PRIMARY KEY,
    titulo VARCHAR(250),
    autor VARCHAR(250),
    editora VARCHAR(250),
    ano_publicacao INT,
    quantidade_disponivel INT,
    categoria VARCHAR(250)
);