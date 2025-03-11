INSERT INTO cliente(
	data_cadastro, telefone, email, nome)
	VALUES ('2024-11-09', '44998240563', 'leonardo@email.com', 'Leonardo'),
	('2024-11-09', '44988745487', 'joao@email.com', 'Joao'),
	('2024-11-09', '44954125879', 'mariao@email.com', 'Maria'),
	('2024-11-09', '44954125880', 'roberto@email.com', 'Roberto');

INSERT INTO livro(
	data_publicacao, autor, categoria, isbn, titulo)
	VALUES ('2024-10-15', 'Autor 1', 'AVENTURA', '1234567891234', 'Livro 1'),
	('2024-10-15', 'Autor 2', 'AVENTURA', '2123456789123', 'Livro 2'),
	('2024-10-15', 'Autor 3', 'AVENTURA', '3123456789123', 'Livro 3'),
	('2024-10-15', 'Autor 4', 'AVENTURA', '4123456789123', 'Livro 4'),
	('2024-10-15', 'Autor 5', 'ROMANCE', '5123456789123', 'Livro 5'),
	('2024-10-15', 'Autor 6', 'ROMANCE', '6123456789123', 'Livro 6'),
	('2024-10-15', 'Autor 7', 'ROMANCE', '7123456789123', 'Livro 7'),
	('2024-10-15', 'Autor 8', 'FICCAO', '8123456789123', 'Livro 8'),
	('2024-10-15', 'Autor 9', 'FICCAO', '9123456789123', 'Livro 9'),
	('2024-10-15', 'Autor 10', 'FICCAO', '1012345678912', 'Livro 10');

INSERT INTO emprestimo(
	data_devolucao, data_emprestimo, livro_id, cliente_id, status)
	VALUES 	('2024-11-09', '2024-10-09', 1, 1, 'DEVOLVIDO'),
	('2024-11-09', '2024-10-09', 2, 1, 'DEVOLVIDO'),
	('2024-11-09', '2024-10-09', 3, 1, 'DEVOLVIDO'),
	('2024-11-09', '2024-10-09', 3, 2, 'DEVOLVIDO'),
	(null, '2024-10-09', 4, 3, 'EMPRESTADO');



