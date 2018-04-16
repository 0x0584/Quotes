-- CREATED: April 15, 2018

create database quotes_db;
use quotes_db; 

create table q_user (
        idUser	 integer auto_increment,
	isAdmin  varchar(3) not null, -- i.e. t/nil
        login	 varchar(20) not null,
        pwd	 varchar(20) not null,
        f_name	 varchar(20) not null,
        l_name	 varchar(20) not null,
        email	 varchar(15) not null,
	-- primary key: idUser
	primary key (idUser)
);

create table q_quote (
        idQuote	 integer auto_increment,
	idAuthor integer not null,	-- original creator
	idUser   integer default null, 	-- when a quote is shared
	created	 date not null,
	content  varchar(255),
	-- primary key: idQuote, idAuthor
	primary key (idQuote, idAuthor),
	-- foregin key: idAuthor
	constraint fk_idauthor 
	foreign key (idAuthor) references q_user(idUser),
	-- foregin key: idUser
      	constraint fk_iduser 
	foreign key (idUser) references q_user(idUser)
);
