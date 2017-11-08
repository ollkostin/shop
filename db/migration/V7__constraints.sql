ALTER TABLE "cart"
  DROP CONSTRAINT cart_product_id_fkey,
  DROP CONSTRAINT cart_user_id_fkey,
  ADD CONSTRAINT cart_product_id_fkey FOREIGN KEY (product_id) REFERENCES "product" (id) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT cart_user_id_fkey FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE;
ALTER TABLE "product_photo"
  DROP CONSTRAINT product_photo_product_id_fkey,
  ADD CONSTRAINT product_photo_product_id_fkey FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE;
