CREATE TABLE treinos (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL REFERENCES usuarios(id),
    estado VARCHAR(20) NOT NULL,
    data_hora_inicio TIMESTAMP,
    data_hora_fim TIMESTAMP,
    volume_total NUMERIC(10,2)
);
