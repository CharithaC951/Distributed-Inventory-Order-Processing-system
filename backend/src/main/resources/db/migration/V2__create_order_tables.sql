CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             product_id BIGINT NOT NULL,
                             quantity INTEGER NOT NULL,
                             order_id BIGINT NOT NULL,
                             CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(id)
);