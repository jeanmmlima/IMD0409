#deletando banco
 drop database aulas;

create database aulas;

#CRIANDO PRIMEIRA TABELA

use aulas;

CREATE TABLE contatos(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(255),
    email VARCHAR(255),
    endereco VARCHAR(255),
    dataNascimento DATE,
    primary key (id)
);
