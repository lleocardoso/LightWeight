CREATE TABLE exercicios (
    id UUID PRIMARY KEY,
    treino_id UUID NOT NULL REFERENCES treinos(id),
    nome VARCHAR(100) NOT NULL,
    series INTEGER NOT NULL,
    repeticoes INTEGER NOT NULL,
    carga NUMERIC(8,2) NOT NULL,
    agrupamentomuscular VARCHAR(20) NOT NULL
);