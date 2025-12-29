CREATE TABLE cliente (
     id SERIAL PRIMARY KEY UNIQUE,
     nome TEXT NOT NULL,
     email TEXT NOT NULL,
     telefone TEXT NOT NULL
);


CREATE TABLE atendente (
    id SERIAL PRIMARY KEY UNIQUE,
    nome TEXT NOT NULL,
    email TEXT NOT NULL,
    setor TEXT NOT NULL
);

ALTER TABLE usuario
ADD COLUMN id_cliente INTEGER,
ADD CONSTRAINT fk_cln_usercln
FOREIGN KEY (id_cliente) REFERENCES cliente (id);

ALTER TABLE usuario
ADD COLUMN id_atendente INTEGER,
ADD CONSTRAINT fk_adt_useradt
FOREIGN KEY (id_atendente) REFERENCES atendente (id);



