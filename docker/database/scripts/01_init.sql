CREATE TABLE marcas (
<<<<<<< Updated upstream
   id SMALLSERIAL,
<<<<<<< Updated upstream
   nome VARCHAR(32) UNIQUE NOT NULL,
=======
   nome VARCHAR(32),
=======
   id SMALLINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(32) UNIQUE NOT NULL,
>>>>>>> Stashed changes
>>>>>>> Stashed changes
   PRIMARY KEY(id)
);

CREATE TABLE modelos (
<<<<<<< Updated upstream
   id SMALLSERIAL,
<<<<<<< Updated upstream
   nome VARCHAR(32) NOT NULL,
=======
   nome VARCHAR(32),
=======
   id SMALLINT GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(32) NOT NULL,
>>>>>>> Stashed changes
>>>>>>> Stashed changes
   marca_id SMALLINT NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT uq_modelo_marca UNIQUE(nome, marca_id),
   FOREIGN KEY (marca_id) REFERENCES marcas(id) ON DELETE CASCADE
);

CREATE TABLE clientes (
   id INTEGER GENERATED ALWAYS AS IDENTITY,
   nome VARCHAR(150) NOT NULL,
   telefone VARCHAR(15) NOT NULL,
   PRIMARY KEY(id)
);

CREATE TABLE carros (
<<<<<<< Updated upstream
   id SERIAL,
<<<<<<< Updated upstream
   placa CHAR(7) UNIQUE NOT NULL,
=======
   placa VARCHAR(7) UNIQUE NOT NULL,
=======
   id INTEGER GENERATED ALWAYS AS IDENTITY,
   placa CHAR(7) UNIQUE NOT NULL,
>>>>>>> Stashed changes
>>>>>>> Stashed changes
   cor SMALLINT NOT NULL,
   modelo_id SMALLINT NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(modelo_id) REFERENCES modelos(id)
);

CREATE TABLE responsaveis_carros (
   cliente_id INTEGER NOT NULL,
   carro_id INTEGER NOT NULL,
   PRIMARY KEY(cliente_id, carro_id),
   FOREIGN KEY(cliente_id) REFERENCES clientes(id) ON DELETE CASCADE,
   FOREIGN KEY(carro_id) REFERENCES carros(id) ON DELETE CASCADE
);

CREATE TABLE precos (
<<<<<<< Updated upstream
   id SMALLSERIAL,
<<<<<<< Updated upstream
   preco DECIMAL(10, 2) NOT NULL CHECK(preco >= 0),
   nome VARCHAR(100) NOT NULL,
=======
   preco DECIMAL(10, 2),
   nome VARCHAR(100),
=======
   id SMALLINT GENERATED ALWAYS AS IDENTITY,
   preco DECIMAL(10, 2) NOT NULL CHECK(preco >= 0),
   nome VARCHAR(100) NOT NULL,
>>>>>>> Stashed changes
>>>>>>> Stashed changes
   descricao TEXT,
   PRIMARY KEY(id)
);

CREATE TABLE entradas (
   carro_id INTEGER NOT NULL,
   entrada TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
   preco_base DECIMAL(10, 2) NOT NULL,
   preco_id SMALLINT,
   saida TIMESTAMPTZ CHECK(saida IS NULL OR saida >= entrada),
   PRIMARY KEY(carro_id, entrada),
   FOREIGN KEY(carro_id) REFERENCES carros(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX uq_entrada_aberta ON entradas(carro_id) WHERE saida IS NULL;
