CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    customer_id BIGINT  NOT NULL,
    created_at  TIMESTAMP DEFAULT NOW(),
    updated_at  TIMESTAMP DEFAULT NOW()
);

CREATE TABLE order_items
(
    id            SERIAL PRIMARY KEY,
    product_id    BIGINT      NOT NULL,
    quantity      INTEGER     NOT NULL,
    seller_id     BIGINT      NOT NULL,
    order_id      BIGINT      NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE
);