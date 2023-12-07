-- create table usuarios (
--                           id serial not null,
--                           nome varchar(255) not null,
--                           cpf varchar(255) not null,
--                           dataDeNascimento Date not null ,
--                           cidade varchar(255) not null,
--                           longradouro varchar(255) not null,
--                           longradouroNumero varchar(255) not null,
--                           telefone varchar(255) not null,
--                           createAt timestamp not null,
--                           updateAt timestamp not null,
--                           carrinho_id int not null,
--                           primary key(id),
--                           constraint unique_cpf unique(cpf),
--                           foreign key(carrinho_id) references carrinhos_de_compras(id))
--
-- create table carrinhos_de_compras (
--                                       id serial not null,
--                                       nome varchar(255) not null,
--                                       carTotal numeric(10,2) not null,
--                                       usuario_id int not null,
--                                       qtdProdutos int,
--                                       primary key(id),
--                                       foreign key(usuario_id) references usuarios(id)
-- )
--
-- create table carrinho_produto (
--                                   id serial not null,
--                                   preco preco numeric(10,2) not null,
--                                   carrinho_id int not null,
--                                   produto_id int not null,
--                                   quantidade int,
--                                   createAt timestamp not null,
--                                   primary key(id),
--                                   foreign key(carrinho_id) references carrinhos_de_compras(id),
--                                   foreign key(produto_id) references produtos(id)
-- )
--
--
-- create table produtos (
--                           id serial not null,
--                           nome varchar(255) not null,
--                           preco numeric(10,2) not null ,
--                           descricao varchar(255) not null,
--                           categoria varchar(30) not null,
--                           createAt timestamp not null,
--                           updateAt timestamp not null,
--                           primary key(id)
-- )
--
--
--
--
--
-- create table produto_imgs(
--                              id serial not null,
--                              file_name varchar(255),
--                              file_type varchar(30),
--                              grp_data bytea,
--                              produto_id int not null,
--                              primary key(id),
--                              foreign key(produto_id) references produtos(id),
--                              constraint unique_file_name unique(file_name)
-- )
