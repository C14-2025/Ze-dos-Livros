CREATE DATABASE c14;
USE c14;

CREATE TABLE livro(
	id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(20) NOT NULL UNIQUE, -- International Standard Book Number
    titulo Varchar(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    ano_publicacao INT,
    status ENUM('DISPONIVEL', 'EMPRESTADO') DEFAULT 'DISPONIVEL',
    data_criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE membro(
	id INT AUTO_INCREMENT PRIMARY KEY,
    primeiro_nome VARCHAR(100) NOT NULL,
    ultimo_nome VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    numero_celular VARCHAR(20),
    status ENUM('ATIVO', 'INATIVO') DEFAULT 'ATIVO',
    data_membro DATE NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE lista_emprestimos(
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_livro INT NOT NULL,
    id_membro INT NOT NULL,
    data_emprestimo INT NOT NULL,
    data_limite INT NOT NULL,
    data_retorno INT NOT NULL, -- SERÁ NULO SE O LIVRO AINDA NÃO TIVER SIDO RETORNADO
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	FOREIGN KEY (id_livro) REFERENCES livro(id) ON DELETE CASCADE,
	FOREIGN KEY(id_membro) REFERENCES membro(id) ON DELETE CASCADE,

    UNIQUE KEY unique_emprestimo_ativo (book_id, data_retorno)
);