package service;

import by.zazybo.domain.bean.Director;
import by.zazybo.domain.bean.Film;
import by.zazybo.domain.dao.exception.DAOException;
import by.zazybo.domain.dao.impl.SQLFilmDAO;
import by.zazybo.domain.service.exception.ServiceException;
import by.zazybo.domain.service.filter.FilmFilter;
import by.zazybo.domain.service.impl.FilmServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FilmServiceImplTest {


    @Mock
    private SQLFilmDAO filmDaoMock;


    @InjectMocks
    @Spy
    private FilmServiceImpl filmService;


    @Before
    public void setUp() throws DAOException {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetById_returnFilmAsJson() throws DAOException,  ServiceException {

        int film_id = 1;
        Film film = new Film(1, "Авиатор", LocalDate.of(2019, 01, 01), "Драма");
        film.setDirector(new Director(1, "имя", "фамилия", LocalDate.of(1937, 01, 01)));
        when(filmDaoMock.getFilmById(film_id)).thenReturn(film);

        String expected = "{\"id\":1,\"name\":\"Авиатор\",\"releaseDate\":{\"year\":2019,\"month\":1,\"day\":1},"
                + "\"genre\":\"Драма\",\"director\":{\"id\":1,\"rel\":\"directors\",\"action\":\"GET\","
                + "\"href\":\"http://localhost:8000/directors/1\"}}";

        String filmJson = filmService.getById(film_id);
        verify(filmDaoMock).getFilmById(film_id);
        Assert.assertEquals(expected, filmJson);
    }

    @Test (expected = ServiceException.class)
    public void testGetById_throwsException() throws DAOException, ServiceException {

        int film_id = 1;
        when(filmDaoMock.getFilmById(film_id)).thenThrow(new DAOException());

        filmService.getById(film_id);
        verify(filmDaoMock).getFilmById(film_id);

    }



    @Test
    public void testGetAll_returnFilmsNoExpandNoFilterAsJson() throws DAOException, ServiceException {

        List<Film> films = new ArrayList<>();

        Film film = new Film(1, "Авиатор", LocalDate.of(2004, 12, 14), "Драма");
        film.setDirector(new Director(1, "имя", "фамилия", LocalDate.of(1937, 01, 01)));

        Film film1 = new Film(2, "Хранитель времени", LocalDate.of(2011, 11, 23), "Приключения");
        film1.setDirector(new Director(3, "имя", "фамилия", LocalDate.of(1987, 07, 9)));

        Film film2 = new Film(3, "Славные парни", LocalDate.of(1990, 12, 9), "Криминальная драма");
        film2.setDirector(new Director(7, "имя", "фамилия", LocalDate.of(1957, 11, 21)));

        films.add(film);
        films.add(film1);
        films.add(film2);

        Map<String, String> params = new HashMap<>();

        when(filmDaoMock.getFilms(any(FilmFilter.class))).thenReturn(films);

        String expected = "[{\"id\":1,\"name\":\"Авиатор\",\"releaseDate\":{\"year\":2004,\"month\":12,\"day\":14},"
                + "\"genre\":\"Драма\",\"director\":{\"id\":1,\"rel\":\"directors\",\"action\":\"GET\","
                + "\"href\":\"http://localhost:8000/directors/1\"}},"
                + "{\"id\":2,\"name\":\"Хранитель времени\",\"releaseDate\":{\"year\":2011,\"month\":11,\"day\":23},"
                + "\"genre\":\"Приключения\",\"director\":{\"id\":3,\"rel\":\"directors\",\"action\":\"GET\","
                + "\"href\":\"http://localhost:8000/directors/3\"}},"
                + "{\"id\":3,\"name\":\"Славные парни\",\"releaseDate\":{\"year\":1990,\"month\":12,\"day\":9},"
                + "\"genre\":\"Криминальная драма\",\"director\":{\"id\":7,\"rel\":\"directors\",\"action\":\"GET\","
                + "\"href\":\"http://localhost:8000/directors/7\"}}]";

        String filmJson = filmService.getAll(params);
        verify(filmDaoMock).getFilms(any(FilmFilter.class));
        Assert.assertEquals(expected, filmJson);
    }

    @Test
    public void testGetAll_returnFilmsExpandNoFilterAsJson() throws DAOException, ServiceException {

        List<Film> films = new ArrayList<>();

        Film film = new Film(1, "Авиатор", LocalDate.of(2004, 12, 14), "Драма");
        film.setDirector(new Director(1, "Мартин", "Скорсезе", LocalDate.of(1942, 11, 17)));

        Film film1 = new Film(2, "Хранитель времени", LocalDate.of(2011, 11, 23), "Приключения");
        film1.setDirector(new Director(1, "Мартин", "Скорсезе", LocalDate.of(1942, 11, 17)));

        Film film2 = new Film(3, "Терминатор: Темные судьбы", LocalDate.of(2019, 10, 23), "Боевик");
        film2.setDirector(new Director(4, "Джеймс", "Кэмерон", LocalDate.of(1954, 8, 16)));

        films.add(film);
        films.add(film1);
        films.add(film2);

        Map<String, String> params = new HashMap<>();
        params.put("expand","directors");

        when(filmDaoMock.getFilms(any(FilmFilter.class))).thenReturn(films);

        String expected = "[{\"id\":1,\"name\":\"Авиатор\",\"releaseDate\":{\"year\":2004,\"month\":12,\"day\":14},"
                + "\"genre\":\"Драма\",\"director\":{\"id\":1,\"firstName\":\"Мартин\",\"lastName\":\"Скорсезе\","
                + "\"birthDate\":{\"year\":1942,\"month\":11,\"day\":17}}},"
                + "{\"id\":2,\"name\":\"Хранитель времени\",\"releaseDate\":{\"year\":2011,\"month\":11,\"day\":23},"
                + "\"genre\":\"Приключения\",\"director\":{\"id\":1,\"firstName\":\"Мартин\",\"lastName\":\"Скорсезе\","
                + "\"birthDate\":{\"year\":1942,\"month\":11,\"day\":17}}},"
                + "{\"id\":3,\"name\":\"Терминатор: Темные судьбы\",\"releaseDate\":{\"year\":2019,\"month\":10,\"day\":23},"
                + "\"genre\":\"Боевик\",\"director\":{\"id\":4,\"firstName\":\"Джеймс\",\"lastName\":"
                + "\"Кэмерон\",\"birthDate\":{\"year\":1954,\"month\":8,\"day\":16}}}]";


        String filmJson = filmService.getAll(params);
        verify(filmDaoMock).getFilms(any(FilmFilter.class));
        Assert.assertEquals(expected, filmJson);

    }

    @Test (expected = ServiceException.class)
    public void testGetAll_throwsException() throws DAOException, ServiceException {

        Map<String, String> params = new HashMap<>();
        when(filmDaoMock.getFilms(any(FilmFilter.class))).thenThrow(new DAOException());

        filmService.getAll(params);
        verify(filmDaoMock).getFilms(any(FilmFilter.class));

    }
}
