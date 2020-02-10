package by.zazybo.api.service.impl;

import by.zazybo.api.bean.Director;
import by.zazybo.api.bean.Film;
import by.zazybo.api.dao.FilmDao;
import by.zazybo.api.dao.exception.DAOException;
import by.zazybo.api.dao.factory.DAOFactory;
import by.zazybo.api.resource.DirectorResource;
import by.zazybo.api.resource.FilmResource;
import by.zazybo.api.service.Service;
import by.zazybo.api.service.exception.DataBaseConnectionServiceException;
import by.zazybo.api.service.exception.ServiceException;
import by.zazybo.api.service.expand.impl.FilmExpand;
import by.zazybo.api.service.factory.ExpandFactory;
import by.zazybo.api.service.filter.FilmFilter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilmServiceImpl implements Service {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private FilmDao filmDAO = daoFactory.getFilmDAO();

    private Gson gson = new Gson();

    public String getById(int id) throws ServiceException {
        try {
            Film filmById = filmDAO.getFilmById(id);
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
            List<Film> films = filmDAO.getFilms(filmFilter);
            for (Film film : films) {
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
