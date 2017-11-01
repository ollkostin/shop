CREATE TABLE order_details (
  order_id   SERIAL,
  product_id SERIAL,
  count      SMALLINT CHECK (count > 0),
  PRIMARY KEY (order_id, product_id),
  FOREIGN KEY (order_id) REFERENCES "order" (id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES "product" (id) ON DELETE RESTRICT
);

ALTER TABLE "order"
  ADD COLUMN total_price DECIMAL DEFAULT 0;