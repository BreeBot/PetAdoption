DROP TABLE IF EXISTS pet_types;
CREATE TABLE pet_types (
  id SERIAL PRIMARY KEY,
  type VARCHAR (255) NOT NULL,
  img_url VARCHAR (500) NOT NULL,
  description TEXT
);
