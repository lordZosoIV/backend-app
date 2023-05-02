-- Insert test categories
INSERT INTO categories (name) VALUES
                                  ('Electronics'),
                                  ('Clothing'),
                                  ('Home and Garden');

-- Insert test products
INSERT INTO products (name, price, seller_id, seller_email, quantity, category_id)
VALUES
    ('iPhone 12', 1099.99, 123456, 'seller@test.com', 10, 1),
    ('Samsung Galaxy S21', 999.99, 654321, 'seller@test.com', 5, 1),
    ('Mens T-shirt', 29.99, 789012, 'seller@test.com', 20, 2),
    ('Womens Dress', 59.99, 345678, 'seller@test.com', 15, 2),
    ('Garden Hose', 19.99, 901234, 'seller@test.com', 50, 3),
    ('LED Light Strip', 39.99, 567890, 'seller@test.com', 8, 1);
