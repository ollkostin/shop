INSERT INTO role VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_VENDOR');

INSERT INTO "user" VALUES
  (1, 'admin', '$2a$04$lq.6AMUnJjxEow6RiII49.6Bi4z5dpaFkSVqbCyxVQP.Vs5UJi9pG');

INSERT INTO user_role VALUES
  (1, 1),
  (1, 2),
  (1, 3);