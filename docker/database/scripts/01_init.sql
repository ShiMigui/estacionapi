CREATE TABLE marcas (
   id SMALLSERIAL,
   nome VARCHAR(32),
   PRIMARY KEY(id)
);

CREATE TABLE modelos (
   id SMALLSERIAL,
   nome VARCHAR(32),
   marca_id SMALLINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (marca_id) REFERENCES marcas(id)
);

CREATE TABLE clientes (
   id SERIAL,
   nome VARCHAR(150) NOT NULL,
   telefone VARCHAR(15) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE carros (
   id SERIAL,
   placa VARCHAR(7) UNIQUE NOT NULL,
   cor SMALLINT NOT NULL,
   modelo_id SMALLINT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(modelo_id) REFERENCES modelos(id)
);

CREATE TABLE responsaveis_carros (
   cliente_id INTEGER NOT NULL,
   carro_id INTEGER NOT NULL,
   PRIMARY KEY(cliente_id, carro_id),
   FOREIGN KEY(cliente_id) REFERENCES clientes(id),
   FOREIGN KEY(carro_id) REFERENCES carros(id)
);

CREATE TABLE precos (
   id SMALLSERIAL,
   preco DECIMAL(10, 2),
   nome VARCHAR(100),
   descricao TEXT,
   PRIMARY KEY(id)
);

CREATE TABLE entradas (
   carro_id INTEGER NOT NULL,
   entrada TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
   preco_base DECIMAL(10, 2) NOT NULL,
   saida TIMESTAMPTZ,
   PRIMARY KEY(carro_id, entrada),
   FOREIGN KEY(carro_id) REFERENCES carros(id)
);
