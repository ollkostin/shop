DROP TRIGGER IF EXISTS clear_cart_trigger on order_details;
DROP FUNCTION IF EXISTS clear_cart;

DROP TRIGGER IF EXISTS count_total_price_trigger on order_details;
DROP FUNCTION IF EXISTS count_total_price_on_trigger();
DROP FUNCTION IF EXISTS set_order_total_price_where_null();
DROP FUNCTION IF EXISTS count_total_price(ord_id integer);

DROP TRIGGER IF EXISTS remove_from_cart_when_removed_from_products on product;
DROP FUNCTION IF EXISTS remove_from_cart_when_removed_from_products();