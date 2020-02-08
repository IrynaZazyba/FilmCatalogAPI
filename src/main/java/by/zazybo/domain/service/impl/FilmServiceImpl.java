package by.zazybo.domain.service.impl;

import by.zazybo.domain.bean.Director;
import by.zazybo.domain.bean.Film;
import by.zazybo.domain.dao.FilmDao;
import by.zazybo.domain.dao.exception.DAOException;
import by.zazybo.domain.dao.factory.DAOFactory;
import by.zazybo.domain.etity.DirectorResource;
import by.zazybo.domain.etity.FilmResource;
import by.zazybo.domain.service.Service;
import by.zazybo.domain.service.exception.DataBaseConnectionServiceException;
import by.zazybo.domain.service.exception.ServiceException;
import by.zazybo.domain.service.expand.impl.FilmExpand;
import by.zazybo.domain.service.factory.ExpandFactory;
import by.zazybo.domain.service.filter.FilmFilter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilmServiceImpl implements Service {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FilmDao sqlFilmDAO = daoFactory.getFilmDAO();

    private Gson gson = new Gson();


    public String getById(int id) throws ServiceException {
        try {
            Film filmById = sqlFilmDAO.getFilmById(id);
            if (filmById == null) {
                return null;
            }
            FilmResource filmResource = transformToFilmResource(filmById, false);

            return gson.toJson(filmResource);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    public String getAll(Map<String, String> params) throws ServiceException {
        FilmFilter filmFilter = new FilmFilter(params);
        ExpandFactory expandFactory = ExpandFactory.getInstance();
        FilmExpand filmExpand = expandFactory.getFilmExpand(params);

        List<FilmResource> filmResources = new ArrayList<>();
        try {
            List<Film> f = sqlFilmDAO.getFilms(filmFilter);
            for (Film film : f) {
                filmResources.add(transformToFilmResource(film, filmExpand.getExpand() != null));
            }

            return gson.toJson(filmResources);
        } catch (DAOException e) {
            throw new DataBaseConnectionServiceException("DataBase connection problem.", e);
        }

    }

    private FilmResource transformToFilmResource(Film film, boolean isExpand) {

        FilmResource filmResource = new FilmResource(film.getId(), film.getName(), film.getReleaseDate(), film.getGenre());
        if (isExpand) {
            filmResource.setDirector(getDirectorExpand(film.getDirector()));
        } else {
            filmResource.setDirector(getDirectorNoExpand(film.getDirector()));
        }

        return filmResource;
    }

    private DirectorResource getDirectorExpand(Director director) {
        return new DirectorResource(director.getId(), director.getFirstName(), director.getLastName(), director.getBirthDate(), false);
    }

    private DirectorResource getDirectorNoExpand(Director director) {
        return new DirectorResource(director.getId());
    }
}
