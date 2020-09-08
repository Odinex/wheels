DROP TABLE IF EXISTS wheels;

CREATE TABLE wheels (
  name VARCHAR(250)  PRIMARY KEY,
  make VARCHAR(250) NOT NULL,
  model VARCHAR(250) NOT NULL,
  variant VARCHAR(250) DEFAULT NULL,
  owner_id int
);

INSERT INTO wheels (name, make, model,owner_id) VALUES
  ('Aliko', 'AUDI', 'A3',1),
  ('Bill', 'AUDI', 'A4',2),
  ('Folrunsho', 'AUDI', 'A5',3);
  COMMIT;
