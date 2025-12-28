CREATE SCHEMA IF NOT EXISTS capability;

CREATE TABLE IF NOT EXISTS capability.capability(
  id SERIAL PRIMARY KEY,
  name VARCHAR(50),
  description VARCHAR(90)

);
--tabla puente entre tecnologia y capacidad
CREATE TABLE IF NOT EXISTS capability.capability_technology (
    capability_id INT NOT NULL,
    technology_id INT NOT NULL,

    PRIMARY KEY (capability_id, technology_id),
    FOREIGN KEY (capability_id) REFERENCES capability.capability(id)
);



