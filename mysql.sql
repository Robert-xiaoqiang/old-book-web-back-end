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
    book_category_name varchar(64) unique not null,
    book_category_intro varchar(128)
)engine = Innodb, default charset = utf8;

create table book_info (
	book_id integer auto_increment primary key,
    book_name varchar(128) not null,
    book_intro varchar(128)
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

# here book is one-by-one
# cannot counting book
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

# here book is one-by-one
# cannot counting book
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
	# must a buyer
    # may not a book_buy
    buyer_id integer references user_info(user_id)
	on delete cascade
    on update cascade,
    
    # subjected to off-line trading
    trade_timestamp timestamp
)engine = Innodb, default charset = utf8;

create table order_detail (
	order_detail_id integer auto_increment primary key,
    order_id integer references order_info(order_id)
	on delete cascade
    on update cascade,
    
    # one-to-one???
    # multiple same books to sell???
    # here is a simple case
    book_sell_id integer unique references book_sell(book_sell_id)
	on delete cascade
    on update cascade
)engine = Innodb, default charset = utf8;

insert into user_info(username, pass_word, email) values('xiaoqiang', '123', 'xiaoqiang@123.top');
insert into user_info(username, pass_word, email) values('xiaoqiang1', '1234', 'xiaoqiang1@1234.top');
insert into user_info(username, pass_word, email) values('xiaoqiang2', '12345', 'xiaoqiang2@12345.top');


insert into book_category_info(book_category_name, book_category_intro) values('A马克思列宁主义', 'A马克思列宁主义啊, 祖国');#1
insert into book_category_info(book_category_name, book_category_intro) values('AA毛泽东思想', 'AA毛泽东思想啊, 祖国');#2
insert into book_category_info(book_category_name, book_category_intro) values('B宗教哲学', 'B宗教哲学啊, 祖国');#3
insert into book_category_info(book_category_name, book_category_intro) values('C社会科学', 'C社会科学啊, 祖国');#4
insert into book_category_info(book_category_name, book_category_intro) values('D政治法律', 'D政治法律啊, 祖国');#5
insert into book_category_info(book_category_name, book_category_intro) values('E军事', 'E军事啊, 祖国');#6
insert into book_category_info(book_category_name, book_category_intro) values('F经济', 'F经济啊, 祖国');#7
insert into book_category_info(book_category_name, book_category_intro) values('G文化体育', 'G文化体育啊, 祖国');#8
insert into book_category_info(book_category_name, book_category_intro) values('H自然语言', 'H自然语言啊, 祖国');#9
insert into book_category_info(book_category_name, book_category_intro) values('I文学艺术', 'I文学艺术啊, 祖国');#10
insert into book_category_info(book_category_name, book_category_intro) values('K自然地理', 'K自然地理啊, 祖国');#11
insert into book_category_info(book_category_name, book_category_intro) values('N自然科学总论', 'N自然科学总论啊, 祖国');#12
insert into book_category_info(book_category_name, book_category_intro) values('O数理科学', 'O数理科学啊, 祖国');#13
insert into book_category_info(book_category_name, book_category_intro) values('P天文学', 'P天文学啊, 祖国');#14
insert into book_category_info(book_category_name, book_category_intro) values('Q生物科学', 'Q生物科学啊, 祖国');#15
insert into book_category_info(book_category_name, book_category_intro) values('R医学卫生', 'R医学卫生啊, 祖国');#16
insert into book_category_info(book_category_name, book_category_intro) values('S农业科学', 'S农业科学啊, 祖国');#17
insert into book_category_info(book_category_name, book_category_intro) values('TD矿业工程', 'TD矿业工程啊, 祖国');#18
insert into book_category_info(book_category_name, book_category_intro) values('TE石油天然气', 'TE石油天然气啊, 祖国');#19
insert into book_category_info(book_category_name, book_category_intro) values('TL原子能', 'TL原子能啊, 祖国');#20
insert into book_category_info(book_category_name, book_category_intro) values('TM电工技术', 'TM电工技术啊, 祖国');#21
insert into book_category_info(book_category_name, book_category_intro) values('TN无线电电信', 'TN无线电电信啊, 祖国');#22
insert into book_category_info(book_category_name, book_category_intro) values('TP自动化计算机技术', 'TP自动化计算机技术啊, 祖国');#23
insert into book_category_info(book_category_name, book_category_intro) values('U交通运输', 'U交通运输啊, 祖国');#24
insert into book_category_info(book_category_name, book_category_intro) values('Z综合性图书', 'Z综合性图书啊, 祖国');#25
