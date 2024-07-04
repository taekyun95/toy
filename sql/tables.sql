alter table cart
    drop
        foreign key FK3d704slv66tw6x5hmbm6p2x3u;

alter table cart
    drop
        foreign key FKg5uhi8vpsuy0lgloxk2h4w5o6;

alter table order_product
    drop
        foreign key FKl5mnj9n0di7k1v90yxnthkc73;

alter table order_product
    drop
        foreign key FKhnfgqyjx3i80qoymrssls3kno;

alter table orders
    drop
        foreign key FK32ql8ubntj5uh44ph9659tiih;

drop table if exists cart;

drop table if exists hibernate_sequence;

drop table if exists order_product;

drop table if exists orders;

drop table if exists product;

drop table if exists `product-id-generator`;

drop table if exists users;

create table cart
(
    id         bigint  not null,
    quantity   integer not null,
    product_id bigint,
    user_id    bigint,
    primary key (id)
) engine = InnoDB;

create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence
values (1);

create table order_product
(
    id             bigint not null,
    price          decimal(38, 2),
    stock_quantity bigint not null,
    order_id       bigint,
    product_id     bigint,
    primary key (id)
) engine = InnoDB;

create table orders
(
    id              bigint       not null,
    order_date_time datetime(6)  not null,
    status          varchar(255) not null,
    user_id         bigint,
    primary key (id)
) engine = InnoDB;

create table product
(
    id             bigint not null,
    name           varchar(255),
    price          decimal(38, 2),
    status         varchar(255),
    stock_quantity bigint not null,
    primary key (id)
) engine = InnoDB;

create table `product-id-generator`
(
    next_val bigint
) engine = InnoDB;

insert into `product-id-generator`
values (1);

create table users
(
    id           bigint       not null,
    username     varchar(255),
    email        varchar(255) not null,
    password     varchar(255) not null,
    phone_number varchar(255) not null,
    roles        varchar(255),
    primary key (id),
    index idx_username (username)
) engine = InnoDB;

alter table cart
    add constraint FK3d704slv66tw6x5hmbm6p2x3u
        foreign key (product_id)
            references product (id);

alter table cart
    add constraint FKg5uhi8vpsuy0lgloxk2h4w5o6
        foreign key (user_id)
            references users (id);

alter table order_product
    add constraint FKl5mnj9n0di7k1v90yxnthkc73
        foreign key (order_id)
            references orders (id);

alter table order_product
    add constraint FKhnfgqyjx3i80qoymrssls3kno
        foreign key (product_id)
            references product (id);

alter table orders
    add constraint FK32ql8ubntj5uh44ph9659tiih
        foreign key (user_id)
            references users (id);

INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (2, 'user2@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6605 9000', 'ROLE_USER', 'user2');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (3, 'user3@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5334 5648', 'ROLE_USER', 'user3');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (4, 'user4@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1899 4047', 'ROLE_USER', 'user4');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (5, 'user5@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8620 1188', 'ROLE_USER', 'user5');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (6, 'user6@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4483 9606', 'ROLE_USER', 'user6');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (7, 'user7@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9046 3068', 'ROLE_USER', 'user7');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (8, 'user8@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3289 7340', 'ROLE_USER', 'user8');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (9, 'user9@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8448 8034', 'ROLE_USER', 'user9');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (10, 'user10@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8900 7331', 'ROLE_USER', 'user10');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (11, 'user11@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6624 2017', 'ROLE_USER', 'user11');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (12, 'user12@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6346 3265', 'ROLE_USER', 'user12');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (13, 'user13@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2957 3190', 'ROLE_USER', 'user13');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (14, 'user14@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7626 5571', 'ROLE_USER', 'user14');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (15, 'user15@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2294 3298', 'ROLE_USER', 'user15');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (16, 'user16@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5344 7670', 'ROLE_USER', 'user16');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (17, 'user17@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6361 5709', 'ROLE_USER', 'user17');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (18, 'user18@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8060 2103', 'ROLE_USER', 'user18');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (19, 'user19@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4039 2810', 'ROLE_USER', 'user19');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (20, 'user20@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3815 1832', 'ROLE_USER', 'user20');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (21, 'user21@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1778 4211', 'ROLE_USER', 'user21');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (22, 'user22@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2509 4368', 'ROLE_USER', 'user22');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (23, 'user23@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9073 6616', 'ROLE_USER', 'user23');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (24, 'user24@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5341 3006', 'ROLE_USER', 'user24');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (25, 'user25@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4370 6642', 'ROLE_USER', 'user25');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (26, 'user26@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8969 5232', 'ROLE_USER', 'user26');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (27, 'user27@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4808 3252', 'ROLE_USER', 'user27');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (28, 'user28@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5181 9729', 'ROLE_USER', 'user28');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (29, 'user29@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7667 3667', 'ROLE_USER', 'user29');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (30, 'user30@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6751 7910', 'ROLE_USER', 'user30');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (31, 'user31@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8748 6656', 'ROLE_USER', 'user31');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (32, 'user32@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7234 5351', 'ROLE_USER', 'user32');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (33, 'user33@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3585 1730', 'ROLE_USER', 'user33');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (34, 'user34@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5264 6918', 'ROLE_USER', 'user34');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (35, 'user35@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7497 5291', 'ROLE_USER', 'user35');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (36, 'user36@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5596 6694', 'ROLE_USER', 'user36');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (37, 'user37@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3011 5673', 'ROLE_USER', 'user37');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (38, 'user38@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6643 9983', 'ROLE_USER', 'user38');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (39, 'user39@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5931 5417', 'ROLE_USER', 'user39');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (40, 'user40@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8194 8866', 'ROLE_USER', 'user40');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (41, 'user41@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9513 4439', 'ROLE_USER', 'user41');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (42, 'user42@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5290 4886', 'ROLE_USER', 'user42');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (43, 'user43@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4178 6656', 'ROLE_USER', 'user43');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (44, 'user44@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6498 3345', 'ROLE_USER', 'user44');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (45, 'user45@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6682 4646', 'ROLE_USER', 'user45');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (46, 'user46@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4760 7898', 'ROLE_USER', 'user46');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (47, 'user47@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8011 2005', 'ROLE_USER', 'user47');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (48, 'user48@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3210 1441', 'ROLE_USER', 'user48');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (49, 'user49@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2460 2176', 'ROLE_USER', 'user49');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (50, 'user50@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1291 9470', 'ROLE_USER', 'user50');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (51, 'user51@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5524 1883', 'ROLE_USER', 'user51');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (52, 'user52@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9306 9485', 'ROLE_USER', 'user52');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (53, 'user53@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7571 6739', 'ROLE_USER', 'user53');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (54, 'user54@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6598 7019', 'ROLE_USER', 'user54');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (55, 'user55@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6233 9125', 'ROLE_USER', 'user55');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (56, 'user56@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7254 1420', 'ROLE_USER', 'user56');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (57, 'user57@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6766 7651', 'ROLE_USER', 'user57');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (58, 'user58@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9476 3673', 'ROLE_USER', 'user58');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (59, 'user59@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5279 7153', 'ROLE_USER', 'user59');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (60, 'user60@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6732 4968', 'ROLE_USER', 'user60');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (61, 'user61@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2379 1331', 'ROLE_USER', 'user61');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (62, 'user62@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9932 8290', 'ROLE_USER', 'user62');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (63, 'user63@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4030 5716', 'ROLE_USER', 'user63');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (64, 'user64@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6432 7521', 'ROLE_USER', 'user64');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (65, 'user65@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2182 7215', 'ROLE_USER', 'user65');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (66, 'user66@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9578 8190', 'ROLE_USER', 'user66');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (67, 'user67@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1415 5282', 'ROLE_USER', 'user67');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (68, 'user68@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9565 1358', 'ROLE_USER', 'user68');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (69, 'user69@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1960 2611', 'ROLE_USER', 'user69');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (70, 'user70@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2156 8831', 'ROLE_USER', 'user70');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (71, 'user71@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4042 3035', 'ROLE_USER', 'user71');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (72, 'user72@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6036 1636', 'ROLE_USER', 'user72');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (73, 'user73@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7549 9867', 'ROLE_USER', 'user73');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (74, 'user74@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3058 9239', 'ROLE_USER', 'user74');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (75, 'user75@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1933 6110', 'ROLE_USER', 'user75');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (76, 'user76@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4219 9565', 'ROLE_USER', 'user76');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (77, 'user77@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6495 9405', 'ROLE_USER', 'user77');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (78, 'user78@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 8262 5103', 'ROLE_USER', 'user78');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (79, 'user79@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1185 6053', 'ROLE_USER', 'user79');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (80, 'user80@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3711 3342', 'ROLE_USER', 'user80');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (81, 'user81@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9955 8051', 'ROLE_USER', 'user81');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (82, 'user82@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6870 7857', 'ROLE_USER', 'user82');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (83, 'user83@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6899 6233', 'ROLE_USER', 'user83');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (84, 'user84@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 9243 4076', 'ROLE_USER', 'user84');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (85, 'user85@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 4470 8078', 'ROLE_USER', 'user85');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (86, 'user86@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3449 1980', 'ROLE_USER', 'user86');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (87, 'user87@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2266 6196', 'ROLE_USER', 'user87');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (88, 'user88@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7393 3955', 'ROLE_USER', 'user88');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (89, 'user89@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5308 1728', 'ROLE_USER', 'user89');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (90, 'user90@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2090 7414', 'ROLE_USER', 'user90');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (91, 'user91@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6849 7127', 'ROLE_USER', 'user91');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (92, 'user92@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 5533 3746', 'ROLE_USER', 'user92');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (93, 'user93@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 2940 7698', 'ROLE_USER', 'user93');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (94, 'user94@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3220 4414', 'ROLE_USER', 'user94');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (95, 'user95@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3469 5998', 'ROLE_USER', 'user95');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (96, 'user96@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 3309 8829', 'ROLE_USER', 'user96');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (97, 'user97@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7056 5561', 'ROLE_USER', 'user97');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (98, 'user98@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 6578 1739', 'ROLE_USER', 'user98');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (99, 'user99@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 7970 6128', 'ROLE_USER', 'user99');
INSERT INTO users (id, email, password, phone_number, roles, username) VALUES (100, 'user100@example.com', '$2a$10$HoMyQzCjJ2sCqeBi0epA4Ofrf2.OaN/9A7ESRcLSCXmwETcvXpHNO', '+82 10 1809 9932', 'ROLE_USER', 'user100');

insert into product(id, name, price, status, stock_quantity) values (1, 'product1', 1010, 'AVAILABLE', 11);
insert into product(id, name, price, status, stock_quantity) values (2, 'product2', 1020, 'AVAILABLE', 12);
insert into product(id, name, price, status, stock_quantity) values (3, 'product3', 1030, 'AVAILABLE', 13);
insert into product(id, name, price, status, stock_quantity) values (4, 'product4', 1040, 'AVAILABLE', 14);
insert into product(id, name, price, status, stock_quantity) values (5, 'product5', 1050, 'AVAILABLE', 15);
insert into product(id, name, price, status, stock_quantity) values (6, 'product6', 1060, 'AVAILABLE', 16);
insert into product(id, name, price, status, stock_quantity) values (7, 'product7', 1070, 'AVAILABLE', 17);
insert into product(id, name, price, status, stock_quantity) values (8, 'product8', 1080, 'AVAILABLE', 18);
insert into product(id, name, price, status, stock_quantity) values (9, 'product9', 1090, 'AVAILABLE', 19);
insert into product(id, name, price, status, stock_quantity) values (10, 'product10', 1100, 'AVAILABLE', 20);
insert into product(id, name, price, status, stock_quantity) values (11, 'product11', 1110, 'AVAILABLE', 21);
insert into product(id, name, price, status, stock_quantity) values (12, 'product12', 1120, 'AVAILABLE', 22);
insert into product(id, name, price, status, stock_quantity) values (13, 'product13', 1130, 'AVAILABLE', 23);
insert into product(id, name, price, status, stock_quantity) values (14, 'product14', 1140, 'AVAILABLE', 24);
insert into product(id, name, price, status, stock_quantity) values (15, 'product15', 1150, 'AVAILABLE', 25);
insert into product(id, name, price, status, stock_quantity) values (16, 'product16', 1160, 'AVAILABLE', 26);
insert into product(id, name, price, status, stock_quantity) values (17, 'product17', 1170, 'AVAILABLE', 27);
insert into product(id, name, price, status, stock_quantity) values (18, 'product18', 1180, 'AVAILABLE', 28);
insert into product(id, name, price, status, stock_quantity) values (19, 'product19', 1190, 'AVAILABLE', 29);
insert into product(id, name, price, status, stock_quantity) values (20, 'product20', 1200, 'AVAILABLE', 30);
insert into product(id, name, price, status, stock_quantity) values (21, 'product21', 1210, 'AVAILABLE', 31);
insert into product(id, name, price, status, stock_quantity) values (22, 'product22', 1220, 'AVAILABLE', 32);
insert into product(id, name, price, status, stock_quantity) values (23, 'product23', 1230, 'AVAILABLE', 33);
insert into product(id, name, price, status, stock_quantity) values (24, 'product24', 1240, 'AVAILABLE', 34);
insert into product(id, name, price, status, stock_quantity) values (25, 'product25', 1250, 'AVAILABLE', 35);
insert into product(id, name, price, status, stock_quantity) values (26, 'product26', 1260, 'AVAILABLE', 36);
insert into product(id, name, price, status, stock_quantity) values (27, 'product27', 1270, 'AVAILABLE', 37);
insert into product(id, name, price, status, stock_quantity) values (28, 'product28', 1280, 'AVAILABLE', 38);
insert into product(id, name, price, status, stock_quantity) values (29, 'product29', 1290, 'AVAILABLE', 39);
insert into product(id, name, price, status, stock_quantity) values (30, 'product30', 1300, 'AVAILABLE', 40);
insert into product(id, name, price, status, stock_quantity) values (31, 'product31', 1310, 'AVAILABLE', 41);
insert into product(id, name, price, status, stock_quantity) values (32, 'product32', 1320, 'AVAILABLE', 42);
insert into product(id, name, price, status, stock_quantity) values (33, 'product33', 1330, 'AVAILABLE', 43);
insert into product(id, name, price, status, stock_quantity) values (34, 'product34', 1340, 'AVAILABLE', 44);
insert into product(id, name, price, status, stock_quantity) values (35, 'product35', 1350, 'AVAILABLE', 45);
insert into product(id, name, price, status, stock_quantity) values (36, 'product36', 1360, 'AVAILABLE', 46);
insert into product(id, name, price, status, stock_quantity) values (37, 'product37', 1370, 'AVAILABLE', 47);
insert into product(id, name, price, status, stock_quantity) values (38, 'product38', 1380, 'AVAILABLE', 48);
insert into product(id, name, price, status, stock_quantity) values (39, 'product39', 1390, 'AVAILABLE', 49);
insert into product(id, name, price, status, stock_quantity) values (40, 'product40', 1400, 'AVAILABLE', 50);
insert into product(id, name, price, status, stock_quantity) values (41, 'product41', 1410, 'AVAILABLE', 51);
insert into product(id, name, price, status, stock_quantity) values (42, 'product42', 1420, 'AVAILABLE', 52);
insert into product(id, name, price, status, stock_quantity) values (43, 'product43', 1430, 'AVAILABLE', 53);
insert into product(id, name, price, status, stock_quantity) values (44, 'product44', 1440, 'AVAILABLE', 54);
insert into product(id, name, price, status, stock_quantity) values (45, 'product45', 1450, 'AVAILABLE', 55);
insert into product(id, name, price, status, stock_quantity) values (46, 'product46', 1460, 'AVAILABLE', 56);
insert into product(id, name, price, status, stock_quantity) values (47, 'product47', 1470, 'AVAILABLE', 57);
insert into product(id, name, price, status, stock_quantity) values (48, 'product48', 1480, 'AVAILABLE', 58);
insert into product(id, name, price, status, stock_quantity) values (49, 'product49', 1490, 'AVAILABLE', 59);
insert into product(id, name, price, status, stock_quantity) values (50, 'product50', 1500, 'AVAILABLE', 60);
insert into product(id, name, price, status, stock_quantity) values (51, 'product51', 1510, 'AVAILABLE', 61);
insert into product(id, name, price, status, stock_quantity) values (52, 'product52', 1520, 'AVAILABLE', 62);
insert into product(id, name, price, status, stock_quantity) values (53, 'product53', 1530, 'AVAILABLE', 63);
insert into product(id, name, price, status, stock_quantity) values (54, 'product54', 1540, 'AVAILABLE', 64);
insert into product(id, name, price, status, stock_quantity) values (55, 'product55', 1550, 'AVAILABLE', 65);
insert into product(id, name, price, status, stock_quantity) values (56, 'product56', 1560, 'AVAILABLE', 66);
insert into product(id, name, price, status, stock_quantity) values (57, 'product57', 1570, 'AVAILABLE', 67);
insert into product(id, name, price, status, stock_quantity) values (58, 'product58', 1580, 'AVAILABLE', 68);
insert into product(id, name, price, status, stock_quantity) values (59, 'product59', 1590, 'AVAILABLE', 69);
insert into product(id, name, price, status, stock_quantity) values (60, 'product60', 1600, 'AVAILABLE', 70);
insert into product(id, name, price, status, stock_quantity) values (61, 'product61', 1610, 'AVAILABLE', 71);
insert into product(id, name, price, status, stock_quantity) values (62, 'product62', 1620, 'AVAILABLE', 72);
insert into product(id, name, price, status, stock_quantity) values (63, 'product63', 1630, 'AVAILABLE', 73);
insert into product(id, name, price, status, stock_quantity) values (64, 'product64', 1640, 'AVAILABLE', 74);
insert into product(id, name, price, status, stock_quantity) values (65, 'product65', 1650, 'AVAILABLE', 75);
insert into product(id, name, price, status, stock_quantity) values (66, 'product66', 1660, 'AVAILABLE', 76);
insert into product(id, name, price, status, stock_quantity) values (67, 'product67', 1670, 'AVAILABLE', 77);
insert into product(id, name, price, status, stock_quantity) values (68, 'product68', 1680, 'AVAILABLE', 78);
insert into product(id, name, price, status, stock_quantity) values (69, 'product69', 1690, 'AVAILABLE', 79);
insert into product(id, name, price, status, stock_quantity) values (70, 'product70', 1700, 'AVAILABLE', 80);
insert into product(id, name, price, status, stock_quantity) values (71, 'product71', 1710, 'AVAILABLE', 81);
insert into product(id, name, price, status, stock_quantity) values (72, 'product72', 1720, 'AVAILABLE', 82);
insert into product(id, name, price, status, stock_quantity) values (73, 'product73', 1730, 'AVAILABLE', 83);
insert into product(id, name, price, status, stock_quantity) values (74, 'product74', 1740, 'AVAILABLE', 84);
insert into product(id, name, price, status, stock_quantity) values (75, 'product75', 1750, 'AVAILABLE', 85);
insert into product(id, name, price, status, stock_quantity) values (76, 'product76', 1760, 'AVAILABLE', 86);
insert into product(id, name, price, status, stock_quantity) values (77, 'product77', 1770, 'AVAILABLE', 87);
insert into product(id, name, price, status, stock_quantity) values (78, 'product78', 1780, 'AVAILABLE', 88);
insert into product(id, name, price, status, stock_quantity) values (79, 'product79', 1790, 'AVAILABLE', 89);
insert into product(id, name, price, status, stock_quantity) values (80, 'product80', 1800, 'AVAILABLE', 90);
insert into product(id, name, price, status, stock_quantity) values (81, 'product81', 1810, 'AVAILABLE', 91);
insert into product(id, name, price, status, stock_quantity) values (82, 'product82', 1820, 'AVAILABLE', 92);
insert into product(id, name, price, status, stock_quantity) values (83, 'product83', 1830, 'AVAILABLE', 93);
insert into product(id, name, price, status, stock_quantity) values (84, 'product84', 1840, 'AVAILABLE', 94);
insert into product(id, name, price, status, stock_quantity) values (85, 'product85', 1850, 'AVAILABLE', 95);
insert into product(id, name, price, status, stock_quantity) values (86, 'product86', 1860, 'AVAILABLE', 96);
insert into product(id, name, price, status, stock_quantity) values (87, 'product87', 1870, 'AVAILABLE', 97);
insert into product(id, name, price, status, stock_quantity) values (88, 'product88', 1880, 'AVAILABLE', 98);
insert into product(id, name, price, status, stock_quantity) values (89, 'product89', 1890, 'AVAILABLE', 99);
insert into product(id, name, price, status, stock_quantity) values (90, 'product90', 1900, 'AVAILABLE', 100);
insert into product(id, name, price, status, stock_quantity) values (91, 'product91', 1910, 'AVAILABLE', 101);
insert into product(id, name, price, status, stock_quantity) values (92, 'product92', 1920, 'AVAILABLE', 102);
insert into product(id, name, price, status, stock_quantity) values (93, 'product93', 1930, 'AVAILABLE', 103);
insert into product(id, name, price, status, stock_quantity) values (94, 'product94', 1940, 'AVAILABLE', 104);
insert into product(id, name, price, status, stock_quantity) values (95, 'product95', 1950, 'AVAILABLE', 105);
insert into product(id, name, price, status, stock_quantity) values (96, 'product96', 1960, 'AVAILABLE', 106);
insert into product(id, name, price, status, stock_quantity) values (97, 'product97', 1970, 'AVAILABLE', 107);
insert into product(id, name, price, status, stock_quantity) values (98, 'product98', 1980, 'AVAILABLE', 108);
insert into product(id, name, price, status, stock_quantity) values (99, 'product99', 1990, 'AVAILABLE', 109);
insert into product(id, name, price, status, stock_quantity) values (100, 'product100', 2000, 'AVAILABLE', 110);
