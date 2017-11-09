--##############################################
-- Изменение таблиц
--##############################################
--Флаг "удален" в таблице продуктов
ALTER TABLE "product"
  ADD COLUMN removed BOOLEAN DEFAULT FALSE;

--##############################################
-- Хранимые процедуры
--##############################################
--Удаление продукта из корзины при удалении из таблицы продуктов
CREATE OR REPLACE FUNCTION remove_from_cart_when_removed_from_products()
  RETURNS TRIGGER AS
$$
BEGIN
  DELETE FROM "cart" c
  USING product p
  WHERE c.product_id = NEW.id
        AND p.removed = TRUE;
  RETURN NULL;
END
$$
LANGUAGE PLPGSQL;

CREATE TRIGGER remove_from_cart_when_removed_from_products
AFTER UPDATE ON "product"
FOR EACH ROW EXECUTE PROCEDURE remove_from_cart_when_removed_from_products();
