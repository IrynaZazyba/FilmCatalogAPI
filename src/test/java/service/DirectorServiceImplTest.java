package service;

import by.zazybo.domain.bean.Director;
import by.zazybo.domain.bean.Film;
import by.zazybo.domain.dao.exception.DAOException;
import by.zazybo.domain.dao.impl.SQLDirectorDAO;
import by.zazybo.domain.service.exception.ServiceException;
import by.zazybo.domain.service.impl.DirectorServiceImpl;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DirectorServiceImplTest {


    @Mock
    private SQLDirectorDAO directorDAOMock;


    @InjectMocks
    @Spy
    private DirectorServiceImpl directorService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetById_returnDirectorAsJson() throws DAOException, ServiceException {

        int director_id = 1;
        Director director = new Director(1, "Мартин", "Скорсезе", LocalDate.of(1942, 11, 17));
        Film film = new Film(1, "Авиатор", LocalDate.of(2019, 01, 01), "Драма");
        Film film1 = new Film(2, "Хранитель времени", LocalDate.of(2011, 11, 23), "Приключения");
        Film film2 = new Film(3, "Славные парни", LocalDate.of(1990, 12, 9), "Криминальная драма");
        director.addFilm(film);
        director.addFilm(film1);
        director.addFilm(film2);

        when(directorDAOMock.getDirectorById(director_id)).thenReturn(director);

        String expected = "{\"id\":1,\"firstName\":\"Мартин\",\"lastName\":\"Скорсезе\",\"birthDate\":"
                + "{\"year\":1942,\"month\":11,\"day\":17},\"films\":[{\"id\":1,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/1\",\"action\":\"GET\"},{\"id\":2,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/2\",\"action\":\"GET\"},{\"id\":3,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/3\",\"action\":\"GET\"}]}";

        String filmJson = directorService.getById(director_id);
        verify(directorDAOMock).getDirectorById(director_id);
        Assert.assertEquals(expected, filmJson);
    }

    @Test(expected = ServiceException.class)
    public void testGetById_throwsException() throws DAOException, ServiceException {

        int director_id = 1;
        when(directorDAOMock.getDirectorById(director_id)).thenThrow(new DAOException());

        directorService.getById(director_id);
        verify(directorDAOMock).getDirectorById(director_id);
    }


    @Test
    public void testGetAll_returnDirectorAsJsonNoExpand() throws DAOException, ServiceException {

        List<Director> directors = new ArrayList<>();
        Director director = new Director(1, "Мартин", "Скорсезе", LocalDate.of(1942, 11, 17));
        Film film = new Film(1, "Авиатор", LocalDate.of(2019, 01, 01), "Драма");
        Film film1 = new Film(2, "Хранитель времени", LocalDate.of(2011, 11, 23), "Приключения");
        Film film2 = new Film(3, "Славные парни", LocalDate.of(1990, 12, 9), "Криминальная драма");
        director.addFilm(film);
        director.addFilm(film1);
        director.addFilm(film2);

        Director director1 = new Director(2, "Квентин", "Тарантино", LocalDate.of(1963, 03, 27));
        Film film3 = new Film(4, "Омерзитльная восьмерка", LocalDate.of(2016, 01, 01), "Вестерн");
        director1.addFilm(film3);

        Director director2 = new Director(3, "София", "Коппола", LocalDate.of(1971, 05, 14));

        directors.add(director);
        directors.add(director1);
        directors.add(director2);

        Map<String, String> params = new HashMap<>();

        when(directorDAOMock.getDirectors()).thenReturn(directors);

        String expected = "[{\"id\":1,\"firstName\":\"Мартин\",\"lastName\":\"Скорсезе\",\"birthDate\":"
                + "{\"year\":1942,\"month\":11,\"day\":17},\"films\":[{\"id\":1,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/1\",\"action\":\"GET\"},{\"id\":2,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/2\",\"action\":\"GET\"},{\"id\":3,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/3\",\"action\":\"GET\"}]},"
                + "{\"id\":2,\"firstName\":\"Квентин\",\"lastName\":\"Тарантино\",\"birthDate\":"
                + "{\"year\":1963,\"month\":3,\"day\":27},\"films\":[{\"id\":4,\"rel\":\"films\","
                + "\"href\":\"http://localhost:8000/films/4\",\"action\":\"GET\"}]},"
                + "{\"id\":3,\"firstName\":\"София\",\"lastName\":\"Коппола\",\"birthDate\":"
                + "{\"year\":1971,\"month\":5,\"day\":14},\"films\":[]}]";

        String filmJson = directorService.getAll(params);
        verify(directorDAOMock).getDirectors();
        Assert.assertEquals(expected, filmJson);
    }

    @Test
    public void testGetAll_returnDirectorAsJsonExpand() throws DAOException, ServiceException {

        List<Director> directors = new ArrayList<>();
        Director director = new Director(1, "Мартин", "Скорсезе", LocalDate.of(1942, 11, 17));
        Film film = new Film(1, "Авиатор", LocalDate.of(2004, 12, 14), "Драма");
        Film film1 = new Film(2, "Хранитель времени", LocalDate.of(2011, 11, 23), "Приключения");
        Film film2 = new Film(3, "Славные парни", LocalDate.of(1990, 12, 9), "Криминальная драма");
        director.addFilm(film);
        director.addFilm(film1);
        director.addFilm(film2);

        Director director1 = new Director(2, "Квентин", "Тарантино", LocalDate.of(1963, 03, 27));
        Film film3 = new Film(4, "Омерзительная восьмерка", LocalDate.of(2016, 01, 01), "Вестерн");
        director1.addFilm(film3);

        Director director2 = new Director(3, "София", "Коппола", LocalDate.of(1971, 05, 14));

        directors.add(director);
        directors.add(director1);
        directors.add(director2);

        Map<String, String> params = new HashMap<>();
        params.put("expand", "films");

        when(directorDAOMock.getDirectors()).thenReturn(directors);

        String expected = "[{\"id\":1,\"firstName\":\"Мартин\",\"lastName\":\"Скорсезе\",\"birthDate\":"
                + "{\"year\":1942,\"month\":11,\"day\":17},\"films\":[{\"id\":1,\"name\":\"Авиатор\","
                + "\"releaseDate\":{\"year\":2004,\"month\":12,\"day\":14},\"genre\":\"Драма\"},"
                + "{\"id\":2,\"name\":\"Хранитель времени\",\"releaseDate\":{\"year\":2011,\"month\":11,\"day\":23},"
                + "\"genre\":\"Приключения\"},{\"id\":3,\"name\":\"Славные парни\",\"releaseDate\":"
                + "{\"year\":1990,\"month\":12,\"day\":9},\"genre\":\"Криминальная драма\"}]},"
                + "{\"id\":2,\"firstName\":\"Квентин\",\"lastName\":\"Тарантино\",\"birthDate\":"
                + "{\"year\":1963,\"month\":3,\"day\":27},\"films\":[{\"id\":4,\"name\":\"Омерзительная восьмерка\","
                + "\"releaseDate\":{\"year\":2016,\"month\":1,\"day\":1},\"genre\":\"Вестерн\"}]},"
                + "{\"id\":3,\"firstName\":\"София\",\"lastName\":\"Коппола\",\"birthDate\":"
                + "{\"year\":1971,\"month\":5,\"day\":14},\"films\":[]}]";


        String filmJson = directorService.getAll(params);

        verify(directorDAOMock).getDirectors();
        Assert.assertEquals(expected, filmJson);

    }

    @Test (expected = ServiceException.class)
    public void testGetAll_throwsException() throws DAOException, ServiceException {

        Map<String, String> params = new HashMap<>();
        when(directorDAOMock.getDirectors()).thenThrow(new DAOException());

        directorService.getAll(params);
        verify(directorDAOMock).getDirectors();

    }
}
