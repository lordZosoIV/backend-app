INSERT INTO deliveries (status, order_id, amount, created_at, updated_at)
VALUES
    ('PENDING', 1001, 25.99, NOW(), NOW()),
    ('DELIVERED', 1002, 45.50, NOW(), NOW()),
    ('CANCELED', 1003, 99.99, NOW(), NOW());