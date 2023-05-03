CREATE TABLE deliveries
(
    id         SERIAL PRIMARY KEY,
    status     VARCHAR(64) NOT NULL,
    order_id   BIGINT      NOT NULL,
    amount     DOUBLE PRECISION,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW()
);