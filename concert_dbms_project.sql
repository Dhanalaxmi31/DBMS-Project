create database project;
use project;

-- CONCERT TABLE --------------------------------------
create table concert(
concert_id int primary key,
concert_name varchar(20),
date date,
venue_id int ,
foreign key (venue_id ) references venue(venue_id ));

select * from concert;


drop table concert;

insert into concert values(101,"BTS",'2024-10-10',1);
insert into concert values(102,"Bron Pink",'2023-11-23',2);
insert into concert values(103,"TeluguStandUpComedy",'2023-09-27',3);
insert into concert values(104,"Dandiya And Garba",'2024-09-30',4);
insert into concert values(105,"Divine Melodies",'2023-10-29',5);







-- ORGANIZER TABLE ---------------------------------------------------------
create table organizer(
organizers_id int primary key,
concert_id int,
organizer_name varchar(20),
location varchar(20),
contact_info bigint,
foreign key (concert_id) references concert(concert_id)
);
drop table  organizer;
select * from organizer;

insert into organizer values(10,101,'Ambani','Mumbai',9573606703);
insert into organizer values(20,102,'Asra','Manipur',957363987);
insert into organizer values(30,103,'Devika','Delhi',95731984);
insert into organizer values(40,104,'Dhana','Nizamabad',957320857);
insert into organizer values(50,105,'Smitha','Tarnaka',9573714254);



--  ARTIST TABLE -----------------------------------------------------------------------------

create table artist(
artist_id int primary key,
concert_id int,
artist_name varchar(20),
genre varchar(20),
contact_info bigint,
venue varchar(20),
foreign key (concert_id) references concert(concert_id)
);

select * from artist;

drop table artist;
insert into artist values(55,101,'Prabhas','Drama',8478392643,'Banjara Hills');
insert into artist values(65,102,'Genny','Singer',8472349643,'Lake view');
insert into artist values(66,103,'Taylorswift','Kpop Singer',8423742643,'King palace');
insert into artist values(85,104,'Lady gaga','Pop singer',8472194643,'Glass House');
insert into artist values(95,105,'MS Subbalaxmi','singer',89073462643,'Hotel Hyatt');


-- VENUE TABLE --------------------------------------------------------------------
create  table venue(
venue_id int primary key,
venue_name varchar(20),
location varchar(20),
capacity int,
ticket int);

drop table  venue;

insert into venue values(1,'Romoji Film City','Hyderabad',1500,2000);
insert into venue values(2,'Taj Hotel','Narayanaguda',2000,3000);
insert into venue values(3,'Red Rose Palace','Madapur',3000,5000);
insert into venue values(4,'Eden Garden','Manikonda',1000,4000);
insert into venue values(5,'Vintage Palace','Uppal',5000,15000);

select * from venue;



-- TICKET TABLE--------------------------------------------------------------------
create table ticket(
ticket_id int primary key,
concert_id int,
foreign key (concert_id) references concert(concert_id)
);

select * from ticket;
insert into ticket values(1305,101);
insert into ticket values(1306,102);
insert into ticket values(1307,103);
insert into ticket values(1308,104);
insert into ticket values(1309,105);



-- ATTENDEE TABLE--------------------------------------------------------------------



create table attendee(
attendee_id int primary key,
attendee_name varchar(20),
attendee_email varchar(20),
contact_info bigint,
ticket_id int,
foreign key (ticket_id) references ticket(ticket_id));

select * from attendee;

insert into attendee values(1098,'Asra','asra229@gmail.com',990124909,1305);
insert into attendee values(1088,'Dhana','dhana239@gmail.com',993525097,1306);
insert into attendee values(1089,'Devika','devika22@gmail.com',999284777,1307);
insert into attendee values(1045,'Smitha','smitha549@gmail.com',999714197,1308);
insert into attendee values(1065,'Ashu','ashu039@gmail.com',99027597,1309);
