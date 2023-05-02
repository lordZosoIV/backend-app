INSERT INTO users (email, name, password, active, address, phone_number)
VALUES ('admin@test.com', 'ADMIN',
        '$2a$10$f9AwetKGg1AdsEFz9gkoFu2YaZCHBBat7AM3.sYQbPFbg.xZrP6X2', true,
        'TBILISI STREET', '+995 599 22 75 21'),
       ('client@test.com', 'CLIENT',
        '$2a$10$mNsTkkPYAu5p1/KtZQmOz.NDA6h3cvKAA8.T79NGUi.tFUnwCpU3m', true,
        'TBILISI STREET', '+995 599 22 75 21'),
       ('seller@test.com', 'SELLER',
        '$2a$10$BpVJ5Sf9b.pb1mIphone_numberPXFq6Tuq0iMZGpxm9i5jd/jtdFUN/FPbYhPn0e', true,
        'TBILISI STREET', '+995 599 22 75 21');

INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON
        (u.email = 'admin@test.com' AND r.name = 'ADMINISTRATOR') OR
        (u.email = 'seller@test.com' AND r.name = 'SELLER') OR
        (u.email = 'client@test.com' AND r.name = 'CLIENT');
