# drop database old_book_circulation_system;
create database old_book_circulation_system;
use old_book_circulation_system;

create table user_info (
	user_id integer auto_increment primary key,
    username varchar(32) unique not null,
    pass_word varchar(32),
    email varchar(32) unique not null
)engine = Innodb, default charset = utf8;

create table book_category_info (
	book_category_id integer auto_increment primary key,
    book_category_intro varchar(128) not null
)engine = Innodb, default charset = utf8;

create table book_info (
	book_id integer auto_increment primary key,
    book_name varchar(64) not null,
    book_intro varchar(64)
)engine = Innodb, default charset = utf8;

create table book_with_category (
	book_id integer not null,
    book_category_id integer not null,
    foreign key(book_id) references book_info(book_id)
    on delete cascade
    on update cascade,
    foreign key(book_category_id) references book_category_info(book_category_id)
	on delete cascade
    on update cascade,
    primary key(book_id, book_category_id)
)engine = Innodb, default charset = utf8;

create table book_sell (
	book_sell_id integer auto_increment primary key,
    book_id integer references book_info(book_id)
	on delete cascade
    on update cascade,
    seller_id integer references user_info(user_id)
	on delete cascade
    on update cascade,
    origin_price decimal(10, 5) not null,
    sell_price decimal(10, 5) not null
)engine = Innodb, default charset = utf8;

create table book_buy (
	book_buy_id integer auto_increment primary key,
	book_id integer references book_info(book_id)
	on delete cascade
    on update cascade,
    buyer_id integer references user_info(user_id)
	on delete cascade
    on update cascade,
    lower_price decimal(10, 5) not null,
    upper_price decimal(10, 5) not null
)engine = Innodb, default charset = utf8;

create table order_info (
	order_id integer auto_increment primary key,
    order_timestamp timestamp not null,
    trade_place varchar(32) not null,
    
    # subjected to off-line trading
    trade_timestamp timestamp
)engine = Innodb, default charset = utf8;

create table order_detail (
	order_detail_id integer auto_increment primary key,
    order_id integer references order_info(order_id)
	on delete cascade
    on update cascade,
    book_sell_id integer references book_sell(book_sell_id)
	on delete cascade
    on update cascade,
    # must a buyer
    # may not a book_buy
    buyer_id integer references user_info(user_id)
	on delete cascade
    on update cascade
)engine = Innodb, default charset = utf8;

insert into user_info(username, pass_word, email) values('xiaoqiang', '123', 'xiaoqiang@123.top');
insert into user_info(username, pass_word, email) values('xiaoqiang1', '1234', 'xiaoqiang1@1234.top');
insert into user_info(username, pass_word, email) values('xiaoqiang2', '12345', 'xiaoqiang2@12345.top');
