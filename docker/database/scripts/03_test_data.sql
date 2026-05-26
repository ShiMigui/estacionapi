INSERT INTO clientes (nome, telefone) VALUES
('João Silva', '11987654321'),
('Maria Oliveira', '21999887766'),
('Carlos Souza', '31988776655'),
('Fernanda Lima', '41995554433');

INSERT INTO carros (placa, cor, modelo_id) VALUES
('ABC1D23', 1, 1),
('BRA2E45', 2, 4),
('CAR3F67', 3, 9),
('DEV4G89', 4, 14),
('LOL5H10', 5, 20),
('SQL6J11', 2, 25),
('API7K12', 1, 30);

INSERT INTO entradas (
    carro_id,
    cliente_id,
    entrada,
    preco_base,
    preco_id,
    saida
) VALUES
( 1, 1, CURRENT_TIMESTAMP - INTERVAL '3 hours', 100.00, 1, CURRENT_TIMESTAMP - INTERVAL '1 hour'),
( 3, 2, CURRENT_TIMESTAMP - INTERVAL '5 hours', 200.00, 3, CURRENT_TIMESTAMP - INTERVAL '4 hours'),
( 5, 4, CURRENT_TIMESTAMP - INTERVAL '1 hour', 150.00, 2, CURRENT_TIMESTAMP - INTERVAL '20 minutes'),
( 6, 4, CURRENT_TIMESTAMP - INTERVAL '8 hours', 100.00, 1, CURRENT_TIMESTAMP - INTERVAL '6 hours'),
( 2, 1, CURRENT_TIMESTAMP - INTERVAL '2 hours', 150.00, 2, NULL),
( 4, 3, CURRENT_TIMESTAMP - INTERVAL '30 minutes', 100.00, 1, NULL),
( 7, 2, CURRENT_TIMESTAMP - INTERVAL '15 minutes', 200.00, 3, NULL);
