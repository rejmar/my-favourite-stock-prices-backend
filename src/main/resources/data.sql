DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS favourite_stock;

CREATE TABLE stock (
  id IDENTITY NOT NULL PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  price DECIMAL(10,2) NOT NULL
);

CREATE TABLE favourite_stock (
    stock_id BIGINT,
    name VARCHAR(250) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
    foreign key (stock_id) references stock(id)
);

INSERT INTO stock (name, price) VALUES
  ('APPLE', RAND()*(1500-1)+1),
  ('MICROSOFT', RAND()*(1500-1)+1),
  ('IG_GROUP', RAND()*(1500-1)+1),
  ('TESLA', RAND()*(1500-1)+1),
  ('GENERAL_MOTORS', RAND()*(1500-1)+1);