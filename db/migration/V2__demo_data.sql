--##############################################
-- Демонстрационные данные
--##############################################
-- Демо данные с информацией о продуктах
INSERT INTO product
VALUES
  (1, 'product_1', 100, 'very good product 1'),
  (2, 'product_2', 200, 'very good product 2'),
  (3, 'product_3', 150, 'very good product 3'),
  (4, 'product_4', 160, 'very good product 4'),
  (5, 'product_5', 170, 'very good product 5'),
  (6, 'product_6', 1500, 'very good product 6'),
  (7, 'product_7', 100, 'very good product 7'),
  (8, 'product_8', 200, 'very good product 8'),
  (9, 'product_9', 150, 'very good product 9'),
  (10, 'product_10', 160, 'very good product 10'),
  (11, 'product_11', 170, 'very good product 11'),
  (12, 'product_12', 1500, 'very good product 12');

ALTER SEQUENCE product_id_seq RESTART WITH 13;

-- Демо данные с информацией об изображениях продуктов
INSERT INTO product_photo
VALUES
  (1, 1, 'product_1_760133746'),
  (2, 2, 'product_2_532581550'),
  (3, 3, 'product_3_1949180875'),
  (4, 4, 'product_4_1244683797'),
  (5, 5, 'product_5_57926432'),
  (6, 6, 'product_6_1338927418'),
  (7, 7, 'product_7_1979020460'),
  (8, 8, 'product_8_707559642'),
  (9, 9, 'product_9_1794468271'),
  (10, 10, 'product_10_665485050'),
  (11, 11, 'product_11_1301593830'),
  (12, 12, 'product_12_672971357'),
  (13, 1, 'product_1_897786468'),
  (14, 1, 'product_1_1224339260'),
  (15, 1, 'product_1_1320835239');

ALTER SEQUENCE product_photo_id_seq RESTART WITH 16;

-- Роли
INSERT INTO role VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_VENDOR');

-- Демонстрационный пользователь-администратор
INSERT INTO "user" VALUES
  (1, 'admin', '$2a$04$lq.6AMUnJjxEow6RiII49.6Bi4z5dpaFkSVqbCyxVQP.Vs5UJi9pG');

ALTER SEQUENCE user_id_seq RESTART WITH 2;

INSERT INTO user_role VALUES
  (1, 1),
  (1, 2),
  (1, 3);

ALTER SEQUENCE role_id_seq RESTART WITH 4;