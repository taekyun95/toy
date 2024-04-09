# Dropping Foreign Keys:
ALTER TABLE cart DROP FOREIGN KEY FK3d704slv66tw6x5hmbm6p2x3u;
ALTER TABLE cart DROP FOREIGN KEY FKg5uhi8vpsuy0lgloxk2h4w5o6;
ALTER TABLE order_product DROP FOREIGN KEY FKl5mnj9n0di7k1v90yxnthkc73;
ALTER TABLE order_product DROP FOREIGN KEY FKhnfgqyjx3i80qoymrssls3kno;
ALTER TABLE orders DROP FOREIGN KEY FK32ql8ubntj5uh44ph9659tiih;

# Dropping Tables:
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS hibernate_sequence;
DROP TABLE IF EXISTS order_product;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS `product-id-generator`;
DROP TABLE IF EXISTS users;

# Creating Tables:
CREATE TABLE cart (
                      id BIGINT NOT NULL,
                      quantity INTEGER NOT NULL,
                      product_id BIGINT,
                      user_id BIGINT,
                      PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE hibernate_sequence (
                                    next_val BIGINT
) ENGINE=InnoDB;
INSERT INTO hibernate_sequence VALUES (1);

CREATE TABLE order_product (
                               id BIGINT NOT NULL,
                               price DECIMAL(38,2),
                               stock_quantity BIGINT NOT NULL,
                               order_id BIGINT,
                               product_id BIGINT,
                               PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE orders (
                        id BIGINT NOT NULL,
                        order_date_time DATETIME(6) NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        user_id BIGINT,
                        PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE product (
                         id BIGINT NOT NULL,
                         name VARCHAR(255),
                         price DECIMAL(38,2),
                         status VARCHAR(255),
                         stock_quantity BIGINT NOT NULL,
                         PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `product-id-generator` (
                                        next_val BIGINT
) ENGINE=InnoDB;
INSERT INTO `product-id-generator` VALUES (1);

CREATE TABLE users (
                       id BIGINT NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(255) NOT NULL,
                       roles VARCHAR(255),
                       username VARCHAR(255),
                       PRIMARY KEY (id)
) ENGINE=InnoDB;

# Adding Constraints:
ALTER TABLE cart ADD CONSTRAINT FK3d704slv66tw6x5hmbm6p2x3u FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE cart ADD CONSTRAINT FKg5uhi8vpsuy0lgloxk2h4w5o6 FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE order_product ADD CONSTRAINT FKl5mnj9n0di7k1v90yxnthkc73 FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE order_product ADD CONSTRAINT FKhnfgqyjx3i80qoymrssls3kno FOREIGN KEY (product_id) REFERENCES product (id);
ALTER TABLE orders ADD CONSTRAINT FK32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id) REFERENCES users (id);
