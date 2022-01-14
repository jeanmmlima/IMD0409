create database agendadev;

use agendadev;

create table contatos(
	id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    primary key(id)
);

insert into contatos (nome,telefone,email) values ('João','(84) 99999-9999','joao@imd.ufrn.br');
insert into contatos (nome,telefone,email) values ('José','(84) 99999-9998','jose@imd.ufrn.br');
insert into contatos (nome,telefone,email) values ('Maria','(84) 99999-9997','maria@imd.ufrn.br');
insert into contatos (nome,telefone,email) values ('Pedro','(84) 99999-9996','pedro@imd.ufrn.br');
