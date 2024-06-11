INSERT INTO users (id, email, password, first_name, last_name, is_deleted)
VALUES (1, 'ustomer@gmail.com', '12345678', 'Oleh', 'Popovich', false),
       (2, 'manager@gmail.com', '12345678', 'Oleh', 'Popovich', false),
       (3, 'user2@example.com', 'hashedPassword', 'John2', 'Doe2', false);

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1),(2, 2),(3, 2);