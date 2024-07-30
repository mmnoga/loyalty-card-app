INSERT INTO users (email, first_name, last_name, password, phone, auth_provider)
VALUES
    ('user1@test.com', 'User1First', 'User1Last', 'abc123', '123456789', 'LOCAL'),
    ('user2@test.com', 'User2First', 'User2Last', 'abc123', '123456789', 'LOCAL'),
    ('user3@test.com', 'User3First', 'User3Last', 'abc123', '1233456789', 'LOCAL'),
    ('user4@test.com', 'User4First', 'User4Last', 'abc123', '123456789', 'LOCAL'),
    ('admin1@test.com', 'Admin1First', 'Admin1Last', 'abc123', '123456789', 'LOCAL'),
    ('admin2@test.com', 'Admin2First', 'Admin2Last', 'abc123', '123456789', 'LOCAL'),
    ('useradmin@test.com', 'UserAdminFirst', 'UserAdminLast', 'abc123', '123456789', 'LOCAL');

INSERT INTO user_roles (user_id, role)
VALUES
    ((SELECT id FROM users WHERE email = 'user1@test.com'), 'USER'),
    ((SELECT id FROM users WHERE email = 'user2@test.com'), 'USER'),
    ((SELECT id FROM users WHERE email = 'user3@test.com'), 'USER'),
    ((SELECT id FROM users WHERE email = 'user4@test.com'), 'USER'),
    ((SELECT id FROM users WHERE email = 'admin1@test.com'), 'ADMIN'),
    ((SELECT id FROM users WHERE email = 'admin2@test.com'), 'ADMIN'),
    ((SELECT id FROM users WHERE email = 'useradmin@test.com'), 'USER'),
    ((SELECT id FROM users WHERE email = 'useradmin@test.com'), 'ADMIN');

INSERT INTO loyalty_cards (card_number, user_id, status)
VALUES
    (UUID(), (SELECT id FROM users WHERE email = 'user1@test.com'), 'ACTIVE'),
    (UUID(), (SELECT id FROM users WHERE email = 'user2@test.com'), 'ACTIVE'),
    (UUID(), (SELECT id FROM users WHERE email = 'user3@test.com'), 'ACTIVE'),
    (UUID(), (SELECT id FROM users WHERE email = 'user4@test.com'), 'ACTIVE');

INSERT INTO loyalty_points (card_id, points)
VALUES
    ((SELECT id FROM loyalty_cards WHERE user_id = (SELECT id FROM users WHERE email = 'user1@test.com')), 100),
    ((SELECT id FROM loyalty_cards WHERE user_id = (SELECT id FROM users WHERE email = 'user2@test.com')), 200),
    ((SELECT id FROM loyalty_cards WHERE user_id = (SELECT id FROM users WHERE email = 'user3@test.com')), 300),
    ((SELECT id FROM loyalty_cards WHERE user_id = (SELECT id FROM users WHERE email = 'user4@test.com')), 400);