CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);
