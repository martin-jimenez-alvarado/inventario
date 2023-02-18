CREATE TABLE IF NOT EXISTS categoria (
  id INT NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  PRIMARY KEY (id));

INSERT INTO categoria(id, nombre) VALUES(1, 'Electronicos');
INSERT INTO categoria(id, nombre) VALUES(2, 'Hogar');
INSERT INTO categoria(id, nombre) VALUES(3, 'Aseo');
INSERT INTO categoria(id, nombre) VALUES(4, 'Consumibles');
