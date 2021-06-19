DROP TABLE IF EXISTS adoptable_pets;
CREATE TABLE adoptable_pets (
  id SERIAL PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  img_url VARCHAR (500) NOT NULL,
  age INTEGER,
  vaccination_status BOOLEAN,
  adoption_story TEXT NOT NULL,
  adoption_status VARCHAR (255) NOT NULL,
  type_id INTEGER REFERENCES pet_types(id)
);