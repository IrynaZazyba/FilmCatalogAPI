CREATE DATABASE "filmCatalog"
create table director
(
	id serial not null,
	first_name varchar(32),
	last_name varchar(32),
	birth_date date
);

create unique index director_id_uindex
	on director (id);

alter table director
	add constraint director_pk
		primary key (id);

		create table film
(
	id serial not null,
	director_id int not null
		constraint film_director_id_fk
			references director (id),
	name varchar(32),
	release_date date,
	genre varchar(32)
);

create unique index film_id_uindex
	on film (id);

alter table film
	add constraint film_pk
		primary key (id);
