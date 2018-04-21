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
	created	 timestamp not null default now(),
	content  varchar(255),
	-- primary key: idQuote, idAuthor
	primary key (idQuote, idAuthor),
	-- foregin key: idAuthor
	constraint fk_idauthor
	foreign key (idAuthor) references q_user(idUser),
);

create table q_share (
       idQuote integer,
       idUser integer,
       created timestamp default now(),
       primary key (idQuote, idUser),
       constraint fk_idquote_share 
       foreign key (idQuote) references q_quote(idQuote),
       constraint fk_iduser_share 
       foreign key (idUser) references q_user(idUser)
);

create table q_relation (
	idRelation  integer auto_increment,
	idFollower  integer,
	idFollowing integer,
	created timestamp default now(),
	-- primary key: idRelation
	primary key (idRelation),
	-- foregin keys: idFollower, idFollowing
	constraint fk_idfoer 
	foreign key (idFollower) references q_user(idUser),
	constraint fk_idfowing 
	foreign key (idFollowing) references q_user(idUser)
);

-- qq.idQuote, qq.created, qq.content, qq.idAuthor, qs.idUser, qr.idFollowing, qr.idFollower 

select distinct qq.idQuote, qq.created, qq.idAuthor, qs.idUser  
from q_quote qq, q_user qu, q_relation qr, q_share qs 
where  qu.idUser = 2 and qu.idUser = qr.idFollower and
       (qq.idQuote = qs.idQuote or qq.idAuthor = qu.idUser) and 
       (qs.idUser = qr.idFollowing       or
       qs.idUser = qr.idFollower       or
       qq.idAuthor = qr.idFollowing	or
       qq.idAuthor = qr.idFollower) 
group by (idQuote)
order by created
