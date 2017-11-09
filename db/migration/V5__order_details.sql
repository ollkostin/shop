--##############################################
-- Создание таблиц
--##############################################
-- Таблица с информацией о заказах пользователей
CREATE TABLE order_details (
  order_id   SERIAL,
  product_id SERIAL,
  count      SMALLINT CHECK (count > 0),
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES "product" (id) ON DELETE RESTRICT
);
--##############################################
-- Изменение таблиц
--##############################################
--Стоимость заказа в таблице заказов
ALTER TABLE "order"
  ADD COLUMN total_price DECIMAL;

--##############################################
-- Хранимые процедуры
--##############################################

--Очистка корзины при создании заказа
CREATE OR REPLACE FUNCTION clear_cart()
  RETURNS TRIGGER AS
$$
BEGIN
  DELETE FROM cart c
  USING "order" o, order_details od
  WHERE
    od.product_id = c.product_id
    AND
    c.user_id = o.user_id;
  RETURN NULL;
END
$$
LANGUAGE PLPGSQL;

--Триггер: Очистка корзины при создании заказа
CREATE TRIGGER clear_cart_trigger
AFTER INSERT ON order_details
EXECUTE PROCEDURE clear_cart();

--Подсчет общей стоимости заказа
CREATE OR REPLACE FUNCTION count_total_price(ord_id INTEGER DEFAULT NULL)
  RETURNS DECIMAL AS
$$
DECLARE
  r      RECORD;
  result DECIMAL := 0;
BEGIN
  FOR r IN SELECT
             p.price,
             od.count
           FROM order_details od
             JOIN product p
               ON p.id = od.product_id
           WHERE od.order_id = ord_id
  LOOP
    result := result + r.price * r.count;
  END LOOP;
  RETURN result;
END;
$$
LANGUAGE PLPGSQL;

--Триггерная функция подсчета стоимости заказа
CREATE OR REPLACE FUNCTION count_total_price_on_trigger()
  RETURNS TRIGGER AS
$$
DECLARE
  result DECIMAL := 0;
  ord_id INTEGER;
BEGIN
  IF TG_OP = 'DELETE'
  THEN
    ord_id := OLD.order_id;
  ELSEIF TG_OP = 'INSERT' OR TG_OP = 'UPDATE'
    THEN
      ord_id := NEW.order_id;
  END IF;
  result := count_total_price(ord_id);
  UPDATE "order"
  SET total_price = result
  WHERE id = ord_id;
  RETURN NULL;
END
$$
LANGUAGE PLPGSQL;

--Триггер: подсчет стоимости заказа при вставке, изменении, удалении
CREATE TRIGGER count_total_price_trigger
AFTER INSERT OR UPDATE OR DELETE ON order_details
FOR EACH ROW EXECUTE PROCEDURE count_total_price_on_trigger();

--Обновление стоимости заказов, где не была выставлена
CREATE OR REPLACE FUNCTION set_order_total_price_where_null()
  RETURNS VOID AS
$$
DECLARE
  r RECORD;
BEGIN
  FOR r IN SELECT id AS id
           FROM "order"
           WHERE "order".total_price IS NULL
  LOOP
    UPDATE "order"
    SET total_price = count_total_price(r.id);
  END LOOP;
END;
$$
LANGUAGE PLPGSQL;

--Вызов функции обновления стоимости заказов
SELECT *
FROM set_order_total_price_where_null();