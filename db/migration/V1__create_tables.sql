--##############################################
-- Создание таблиц
--##############################################

-- Таблица ролей пользователей
CREATE TABLE IF NOT EXISTS "role" (
  id   SERIAL,
  name CHARACTER VARYING(20) NOT NULL,
  PRIMARY KEY (id)
);

-- Таблица с данными пользователей
CREATE TABLE IF NOT EXISTS "user" (
  id            SERIAL,
  email         CHARACTER VARYING(255) NOT NULL,
  password_hash CHARACTER VARYING(255) NOT NULL,
  PRIMARY KEY (id)
);

-- Таблица c информацией о ролях конкретных пользователей
CREATE TABLE IF NOT EXISTS "user_role" (
  user_id SERIAL,
  role_id SERIAL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES "user" (id),
  FOREIGN KEY (role_id) REFERENCES "role" (id)
);

-- Таблица товаров
CREATE TABLE IF NOT EXISTS "product" (
  id          SERIAL,
  name        VARCHAR(255) NOT NULL,
  price       DECIMAL      NOT NULL,
  description TEXT,
  PRIMARY KEY (id)
);

-- Таблица с информацией о корзине пользователя
CREATE TABLE IF NOT EXISTS "cart" (
  user_id    SERIAL,
  product_id SERIAL,
  count      SMALLINT CHECK (count > 0),
  PRIMARY KEY (user_id, product_id),
  FOREIGN KEY (product_id) REFERENCES "product" (id) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Таблица с информацией об изображениях продуктов
CREATE TABLE IF NOT EXISTS "product_photo" (
  id         SERIAL,
  product_id SERIAL,
  path       TEXT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Таблица с информацией о заказах пользователей
CREATE TABLE IF NOT EXISTS "order" (
  id      SERIAL,
  date    TIMESTAMP WITH TIME ZONE NOT NULL,
  address TEXT                     NOT NULL,
  user_id SERIAL,
  total_price DECIMAL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES "user" (id)
);

-- Таблица с информацией о заказах пользователей
CREATE TABLE order_details (
  order_id   SERIAL,
  product_id SERIAL,
  count      SMALLINT CHECK (count > 0),
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES "product" (id) ON DELETE RESTRICT
);