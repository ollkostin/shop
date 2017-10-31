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

CREATE TRIGGER clear_cart_trigger
AFTER INSERT ON order_details
EXECUTE PROCEDURE clear_cart();

-- CREATE OR REPLACE FUNCTION order_total_price()
--   RETURNS TRIGGER AS
-- $$
-- DECLARE
--   r      RECORD;
--   result DECIMAL := 0;
-- BEGIN
--   FOR r IN SELECT
--              p.price,
--              od.count
--            FROM order_details od
--              JOIN product p
--                ON p.id = od.product_id
--            WHERE od.order_id = NEW.order_id
--   LOOP
--     result := result + r.price * r.count;
--   END LOOP;
--   UPDATE "order"
--   SET "order".total_price = result
--   WHERE "order".id = NEW.order_id;
--   RETURN NULL ;
-- END;
-- $$
-- LANGUAGE PLPGSQL;
--
-- CREATE TRIGGER count_total_price
-- AFTER INSERT OR UPDATE OR DELETE ON order_details
-- FOR EACH ROW EXECUTE PROCEDURE order_total_price();
