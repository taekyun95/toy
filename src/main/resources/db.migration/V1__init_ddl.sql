-- coupon_issuances
create table coupon_issuances
(
    id                    bigint not null primary key comment 'ID',
    coupon_id             bigint not null comment '쿠폰 ID',
    coupon_issuance_token varchar(255) comment '쿠폰 발급 토큰',
    coupon_token          varchar(255) comment '쿠폰 토큰',
    user_id               bigint not null comment '유저 ID'
) comment 'coupon_issuances' charset = utf8mb4;

create
    index coupon_issuances_idx01 on coupon_issuances (coupon_id);

create
    index coupon_issuances_idx02 on coupon_issuances (user_id);

-- coupons
create table coupons
(
    id            bigint not null primary key comment 'ID',
    count         bigint not null comment '카운트',
    coupon_token  varchar(255) comment '쿠폰 토큰'
) comment 'coupons' charset = utf8mb4;

create
    index coupons_idx01 on coupons (coupon_token);

-- hibernate_sequence
create table hibernate_sequence
(
    next_val bigint
) comment 'hibernate_sequence' charset = utf8mb4;

insert into hibernate_sequence values (1);

-- item_option_groups
create table item_option_groups
(
    id                     bigint not null auto_increment primary key comment 'ID',
    created_at             datetime(6) comment '생성 일시',
    updated_at             datetime(6) comment '수정 일시',
    item_option_group_name varchar(255) comment '옵션 그룹명',
    ordering               integer not null comment '정렬 순서',
    item_id                bigint comment '상품 ID'
) comment 'item_option_groups' charset = utf8mb4;

create
    index item_option_groups_idx01 on item_option_groups (item_id);

create
    index item_option_groups_idx02 on item_option_groups (created_at);

create
    index item_option_groups_idx03 on item_option_groups (updated_at);

-- item_options
create table item_options
(
    id                   bigint not null auto_increment primary key comment 'ID',
    created_at           datetime(6) comment '생성 일시',
    updated_at           datetime(6) comment '수정 일시',
    item_option_name     varchar(255) comment '옵션명',
    item_option_price    bigint not null comment '옵션 가격',
    ordering             integer not null comment '정렬 순서',
    item_option_group_id bigint comment '옵션 그룹 ID'
) comment 'item_options' charset = utf8mb4;

create
    index item_options_idx01 on item_options (item_option_group_id);

create
    index item_options_idx02 on item_options (created_at);

create
    index item_options_idx03 on item_options (updated_at);

-- items
create table items
(
    id            bigint not null primary key comment 'ID',
    item_name     varchar(255) comment '상품명',
    item_price    decimal(38,2) comment '상품 가격',
    item_token    varchar(255) comment '상품 토큰',
    partner_id    bigint not null comment '파트너 ID',
    status        varchar(255) comment '상태'
) comment 'items' charset = utf8mb4;

create
    index items_idx01 on items (item_token);

create
    index items_idx02 on items (partner_id);

-- order_item_option_groups
create table order_item_option_groups
(
    id                     bigint not null auto_increment primary key comment 'ID',
    created_at             datetime(6) comment '생성 일시',
    updated_at             datetime(6) comment '수정 일시',
    item_option_group_name varchar(255) comment '옵션 그룹명',
    ordering               integer not null comment '정렬 순서',
    order_item_id          bigint comment '주문 항목 ID'
) comment 'order_item_option_groups' charset = utf8mb4;

create
    index order_item_option_groups_idx01 on order_item_option_groups (order_item_id);

create
    index order_item_option_groups_idx02 on order_item_option_groups (created_at);

create
    index order_item_option_groups_idx03 on order_item_option_groups (updated_at);

-- order_item_options
create table order_item_options
(
    id                         bigint not null auto_increment primary key comment 'ID',
    created_at                 datetime(6) comment '생성 일시',
    updated_at                 datetime(6) comment '수정 일시',
    item_option_name           varchar(255) comment '옵션명',
    item_option_price          bigint not null comment '옵션 가격',
    ordering                   integer not null comment '정렬 순서',
    order_item_option_group_id bigint comment '주문 항목 옵션 그룹 ID'
) comment 'order_item_options' charset = utf8mb4;

create
    index order_item_options_idx01 on order_item_options (order_item_option_group_id);

create
    index order_item_options_idx02 on order_item_options (created_at);

create
    index order_item_options_idx03 on order_item_options (updated_at);

-- order_items
create table order_items
(
    id              bigint not null auto_increment primary key comment 'ID',
    created_at      datetime(6) comment '생성 일시',
    updated_at      datetime(6) comment '수정 일시',
    delivery_status varchar(255) comment '배송 상태',
    item_id         bigint not null comment '상품 ID',
    item_name       varchar(255) comment '상품명',
    item_price      bigint not null comment '상품 가격',
    item_token      varchar(255) comment '상품 토큰',
    order_count     integer not null comment '주문 수량',
    partner_id      bigint not null comment '파트너 ID',
    order_id        bigint comment '주문 ID'
) comment 'order_items' charset = utf8mb4;

create
    index order_items_idx01 on order_items (item_id);

create
    index order_items_idx02 on order_items (partner_id);

create
    index order_items_idx03 on order_items (order_id);

-- orders
create table orders
(
    id                bigint not null auto_increment primary key comment 'ID',
    etc_message       varchar(255) comment '남기는 말',
    receiver_address1 varchar(255) comment '수령자 주소1',
    receiver_address2 varchar(255) comment '수령자 주소2',
    receiver_name     varchar(255) comment '수령자명',
    receiver_phone    varchar(255) comment '수령자 전화번호',
    receiver_zipcode  varchar(255) comment '수령자 우편번호',
    order_token       varchar(255) not null comment '주문 토큰',
    ordered_at        datetime(6) not null comment '주문 일시',
    pay_method        varchar(255) not null comment '결제 수단',
    status            varchar(255) comment '상태',
    user_id           bigint not null comment '유저 ID'
) comment 'orders' charset = utf8mb4;

create
    index orders_idx01 on orders (order_token);

create
    index orders_idx02 on orders (user_id);

-- partners
create table partners
(
    id            bigint auto_increment primary key comment 'ID',
    partner_token varchar(255) not null comment '파트너 토큰',
    partner_name  varchar(255) not null comment '파트너명',
    business_no   varchar(255) not null comment '사업자 등록 번호',
    email         varchar(255) not null comment '이메일',
    status        varchar(30)  not null default 'ENABLE' comment '상태',
    created_at    datetime(6) not null comment '생성 일시',
    updated_at    datetime(6) comment '수정 일시'
) comment 'partners' charset = utf8mb4;

create
    index partners_idx01 on partners (partner_token);

create
    index partners_idx02 on partners (created_at);

create
    index partners_idx03 on partners (updated_at);

-- users
create table users
(
    id            bigint not null primary key comment 'ID',
    email         varchar(255) not null comment '이메일',
    password      varchar(255) not null comment '비밀번호',
    phone_number  varchar(255) not null comment '전화번호',
    roles         varchar(255) comment '역할',
    user_token    varchar(255) comment '유저 토큰',
    username      varchar(255) comment '유저명'
) comment 'users' charset = utf8mb4;

create
    index users_idx01 on users (email);

create
    index users_idx02 on users (user_token);

-- Foreign key constraints
alter table item_option_groups
    add constraint FK3o9t5ltc98sciaj864scgpi8u
        foreign key (item_id)
            references items (id);

alter table item_options
    add constraint FKbn9jeqh8mguhg6f4hmpi5yab7
        foreign key (item_option_group_id)
            references item_option_groups (id);

alter table order_item_option_groups
    add constraint FK4w8xq7iio2qw66gpkmmag8hy0
        foreign key (order_item_id)
            references order_items (id);

alter table order_item_options
    add constraint FKryer2cms6m6o71sud3f0b4aqg
        foreign key (order_item_option_group_id)
            references order_item_option_groups (id);

alter table order_items
    add constraint FKbioxgbv59vetrxe0ejfubep1w
        foreign key (order_id)
            references orders (id);
