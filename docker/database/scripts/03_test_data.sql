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

INSERT INTO responsaveis_carros (cliente_id, carro_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(2, 7);

INSERT INTO entradas (carro_id, preco_base, saida) VALUES
(1, 100.00, CURRENT_TIMESTAMP + INTERVAL '2 hours'),
(2, 150.00, NULL),
(3, 200.00, CURRENT_TIMESTAMP + INTERVAL '1 hour'),
(4, 100.00, NULL),
(5, 150.00, CURRENT_TIMESTAMP + INTERVAL '30 minutes');
