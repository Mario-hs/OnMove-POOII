CREATE TABLE marcas(
   cdMarca      serial      NOT NULL,
   nome         varchar(50) NOT NULL,
   CONSTRAINT pk_marcas
      PRIMARY KEY(cdMarca)
);

CREATE TABLE modelos(
   cdModelo      serial      NOT NULL,
   descricao     varchar(50) NOT NULL,
   cdMarca       int         NOT NULL,
   CONSTRAINT pk_modelos
      PRIMARY KEY(cdModelo),
   CONSTRAINT fk_modelos_marcas
      FOREIGN KEY(cdMarca)
      REFERENCES marcas(cdMarca)
);

CREATE TABLE bicicletas(
   cdBicicleta    serial      NOT NULL,
   nome           varchar(50) NOT NULL,
   preco          float       NOT NULL,
   quantidade     int         NOT NULL,
   cdModelo       int         NOT NULL,
   CONSTRAINT pk_bicicletas
      PRIMARY KEY(cdBicicleta),
   CONSTRAINT fk_bicicletas_modelos
      FOREIGN KEY(cdModelo)
      REFERENCES modelos(cdModelo)
);

CREATE TABLE clientes(
   cdCliente     serial       NOT NULL,
   nome          varchar(50)  NOT NULL,
   cpf           varchar(50)  NOT NULL,
   data_nasc     date         NOT NULL,
   telefone      varchar(50)  NOT NULL,
   CONSTRAINT pk_clientes
      PRIMARY KEY(cdCliente)
);

CREATE TABLE alugueis(
   cdAluguel        serial    NOT NULL,
   data_emprestimo  date      NOT NULL,
   data_devolucao   date      NOT NULL,
   valor            float     NOT NULL,
   pago             boolean   NOT NULL,
   cdCliente        int,
   CONSTRAINT pk_alugueis
      PRIMARY KEY(cdAluguel),
   CONSTRAINT fk_alugueis_clientes
      FOREIGN KEY(cdCliente)
      REFERENCES clientes(cdCliente)
);

CREATE TABLE itensdealuguel(
   cdItemDeAluguel  serial   NOT NULL,
   quantidade       int      NOT NULL,
   valor            float    NOT NULL,
   cdBicicleta      int,
   cdAluguel        int,
   CONSTRAINT pk_itensdealuguel
      PRIMARY KEY(cdItemDeAluguel),
   CONSTRAINT fk_itensdealuguel_bicicletas
      FOREIGN KEY(cdBicicleta)
      REFERENCES bicicletas(cdBicicleta),
   CONSTRAINT fk_itensdealuguel_alugueis
      FOREIGN KEY(cdAluguel)
      REFERENCES alugueis(cdAluguel)
);

INSERT INTO clientes(nome, cpf, data_nasc, telefone) VALUES('Rafael','111.111.111-11', '01/04/1986','(11) 1111-1111');
INSERT INTO clientes(nome, cpf, data_nasc, telefone) VALUES('João'  ,'222.222.222-22', '01/04/1975','(22) 2222-2222');
INSERT INTO clientes(nome, cpf, data_nasc, telefone) VALUES('Maria' ,'333.333.333-33', '01/04/1994','(33) 3333-3333');

INSERT INTO marcas(nome) VALUES('BMC');
INSERT INTO marcas(nome) VALUES('Pinarello');
INSERT INTO marcas(nome) VALUES('Trek');
INSERT INTO marcas(nome) VALUES('Specialized');
INSERT INTO marcas(nome) VALUES('Raleigh');
INSERT INTO marcas(nome) VALUES('GT');
INSERT INTO marcas(nome) VALUES('Focus');
INSERT INTO marcas(nome) VALUES('Cannondale');
INSERT INTO marcas(nome) VALUES('Jamis');
INSERT INTO marcas(nome) VALUES('Scott');
INSERT INTO marcas(nome) VALUES('Sousa Motors');
INSERT INTO marcas(nome) VALUES('Caloi');


INSERT INTO modelos(descricao) VALUES('Bicicleta Urbana');
INSERT INTO modelos(descricao) VALUES('Mountain');
INSERT INTO modelos(descricao) VALUES('Comum');
INSERT INTO modelos(descricao) VALUES('Speed');
INSERT INTO modelos(descricao) VALUES('BMX');
INSERT INTO modelos(descricao) VALUES('Downhill');
INSERT INTO modelos(descricao) VALUES('Bicicleta Elétrica');


INSERT INTO bicicletas(nome, preco, quantidade, cdModelo) VALUES('Caloi', '70.00', '10', '15');
INSERT INTO bicicletas(nome, preco, quantidade, cdModelo) VALUES('Cannondale', '100.00', '5', '16');
INSERT INTO bicicletas(nome, preco, quantidade, cdModelo) VALUES('Specialized', '120.00', '5', '16');
INSERT INTO bicicletas(nome, preco, quantidade, cdModelo) VALUES('Trek', '115.00', '10', '20');


INSERT INTO alugueis(data_emprestimo, data_devolucao, valor, pago, cdCliente) VALUES('30/04/2016', '10/05/2016', '1200.00', false, '2');
INSERT INTO alugueis(data_emprestimo, data_devolucao, valor, pago, cdCliente) VALUES('01/04/2016',  '07/04/2016', '450.00' , false, '1');


INSERT INTO itensdealuguel(quantidade, valor, cdBicicleta, cdAluguel) VALUES('1', '120.00', '10', '3');
INSERT INTO itensdealuguel(quantidade, valor, cdBicicleta, cdAluguel) VALUES('1', '70.00' , '8', '4');
