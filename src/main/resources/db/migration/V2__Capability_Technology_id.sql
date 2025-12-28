-- Agregar columna id
ALTER TABLE capability.capability_technology
ADD COLUMN id SERIAL;

-- Eliminar PK compuesta
ALTER TABLE capability.capability_technology
DROP CONSTRAINT capability_technology_pkey;

-- Agregar PK simple (requerido por R2DBC)
ALTER TABLE capability.capability_technology
ADD CONSTRAINT capability_technology_pkey PRIMARY KEY (id);

--  Mantener la unicidad lógica
ALTER TABLE capability.capability_technology
ADD CONSTRAINT uk_capability_technology UNIQUE (capability_id, technology_id);

--  Índices para performance
CREATE INDEX idx_cap_tech_capability
    ON capability.capability_technology(capability_id);

CREATE INDEX idx_cap_tech_technology
    ON capability.capability_technology(technology_id);
