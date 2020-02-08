INSERT INTO director(id,first_name,last_name,birth_date)
VALUES (nextval('director_id_seq'),'Мартин', 'Скорсезе', '17.11.1942');

INSERT INTO director(id,first_name,last_name,birth_date)
VALUES (nextval('director_id_seq'),'София', 'Коппола', '14.05.1971');

INSERT INTO director(id,first_name,last_name,birth_date)
VALUES (nextval('director_id_seq'),'Квентин', 'Тарантино', '27.03.1963');

INSERT INTO director(id,first_name,last_name,birth_date)
VALUES (nextval('director_id_seq'),'Джеймс', 'Кэмерон', '16.08.1954');

INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),1,'Авиатор','14.12.2004','Драма');

INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),1,'Хранитель времени','23.11.2011','Приключения');

INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),2,'Омерзительная восьмёрка','01.01.2016','Вестерн');


INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),1,'Славные парни','12.09.1990','Криминальная драма');

INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),1,'Казино','22.11.1995','Драма');

		
INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),4,'Алита: Боевой ангел','31.01.2019','Боевик');

INSERT INTO film (id,director_id,name,release_date,genre)
VALUES (nextval('film_id_seq'),4,'Терминатор: Тёмные судьбы','23.10.2019','Боевик');