# FilmCatalog API
### Описание
REST API приложение на платформе JDK 8+ для поиска в базе данных
информации о режиссерах и созданных ими фильмах.

### Функциональность
- получение всех режиссеров  
GET запрос на endpoint: **/directors** (запрос может быть расширен параметром expand принимающим значение films, т.о. запрос примет вид 
**/directors?expand=films**)

- получение всех фильмов  
GET запрос на endpoint: **/films** (запрос может быть расширен параметром expand принимающим значение directors, т.о. запрос примет вид 
**/films?expand=directors**)

- поиск режисеров по уникальным идентификаторам  
GET запрос на endpoint: **/directors/{id}**

- поиск фильмов по уникальным идентификаторам  
GET запрос на endpoint: **/films/{id}**

- фильтрация фильмов по дате начала периода времени, в течении которого выходили фильмы  
GET запрос на endpoint: **/films?date_start={date_start}** (данный запрос может быть расширен параметром expand принимающим значение directors, т.о. запрос примет вид 
**/films?expand=directors&date_start={date_start}**)

- фильтрация фильмов по идентификатору режисера  
GET запрос на endpoint: **/films?director_id={director_id}** (данный запрос может быть расширен параметром expand принимающим значение directors, т.о. запрос примет вид 
**/films?expand=directors&director_id={director_id}**)

- фильтрация фильмов по дате начала периода времени, в течении которого выходили фильмы и идентификатору режисера  
GET запрос на endpoint: **/films?date_start={date_start}&director_id={director_id}**  (данный запрос может быть расширен параметром expand 
принимающим значение directors, т.о. запрос примет вид  
**/films?expand=directors&date_start={date_start}&director_id={director_id}**)

#### Ограничения
Для endpoint-а **/films** применимы фильтры:
- _date_start_ принимающий значение даты в формате yyyy-mm-dd;
- _director_id_ принимающий уникальный идентификатор режиссера целое число.  

#### Доступные параметры expand
- для endpoint-а **/films** - _expand=directors_
- для endpoint-а **/directors** - _expand=films_
  
  
### Конфигурация базы данных
1. Скачать файл film_catalog_dump.tar расположенный в директории проекта \src\main\resources\film_catalog_dump.tar.
2. Создать базу данных командой **_createdb -U postgres -T template0 FilmCatalog_** (где -U - имя пользователя БД);
3. Командой **pg_restore -U postgres -d FilmCatalog film_catalog_dump.tar_** восстановить структуру и данные (указать полный путь местонахождения файла _film_catalog_dump.tar_.

Для корректной работы приложения сконфигурируйте подключение к базе данных в классе \src\main\java\by\zazybo\api\config\DBConfig.java. По умолчанию установлены следующие значения:
- dbType = "postgresql";
- dbHost = "localhost";
- dbPort = "5432";
- dbUser = "postgres";
- dbPass = "Mastery3101";
- dbName = "FilmCatalog";

### Запуск API
#### С использование Intellij IDEA
1. Распаковать архив с проектом в рабочую директорию.
2. C помощью File -> Open открыть проект в Intellij.
3. Загрузить все необходимые бибилиотеки из файла **pom.xml**
4. Сконфигурировать веб сервер в классе _\src\main\java\by\zazybo\api\config\ServerConfig.java_ указав SERVER_PORT. По умолчанию значение равно SERVER_PORT=8000.
5. Main class для запуска _\src\main\java\by\zazybo\api\runner\Runner.java_.

### Тестирование
API покрыт unit тестами, которые расположены в директории _/src/test_.

### Пример использования
Для отправки запросов на FilmCatalog API можно воспользоваться HTTP клиентом, например Postman, который позволит отправлять HTTP запросы на FilmCatalog API и получать ответ в формате JSON.  
Пример GET запроса: **localhost:8000/films?director_id=2**  
Пример ответа в формате JSON:  
```json
[
    {
        "id": 3,
        "name": "Омерзительная восьмёрка",
        "releaseDate": {
            "year": 2016,
            "month": 1,
            "day": 1
        },
        "genre": "Вестерн",
        "director": {
            "id": 2,
            "rel": "directors",
            "action": "GET",
            "href": "http://localhost:8000/directors/2"
        }
    }
]
```
Ответ содержит ссылки для получения режиссера, связанного с фильмом.
Для получения полной информации о режиссере необходимо указать в запросе параметр expand принимающий значение directors.  
Пример GET запроса с параметром expand: **localhost:8000/films?expand=directors&director_id=2**  
Ответ примет следующий вид:
```json
[
    {
        "id": 3,
        "name": "Омерзительная восьмёрка",
        "releaseDate": {
            "year": 2016,
            "month": 1,
            "day": 1
        },
        "genre": "Вестерн",
        "director": {
            "id": 2,
            "firstName": "Квентин",
            "lastName": "Тарантино",
            "birthDate": {
                "year": 1963,
                "month": 3,
                "day": 27
            }
        }
    }
]
````
К GET запросу на получение всех режиссеров также можно применить параметр _expand_ со значением _films_ и получить полную информацию о фильмах, снятых данными режиссерами, eсли же параметр указан не будет, то в ответе будут содержаться ссылки на фильмы. 

