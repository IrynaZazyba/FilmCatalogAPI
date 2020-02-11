package by.zazybo.api.service.impl;

import by.zazybo.api.bean.Director;
import by.zazybo.api.bean.Film;
import by.zazybo.api.dao.DirectorDao;
import by.zazybo.api.dao.exception.DAOException;
import by.zazybo.api.dao.factory.DAOFactory;
import by.zazybo.api.resource.DirectorResource;
import by.zazybo.api.resource.FilmResource;
import by.zazybo.api.service.Service;
import by.zazybo.api.service.exception.DataBaseConnectionServiceException;
import by.zazybo.api.service.exception.ServiceException;
import by.zazybo.api.service.expand.ExpandInterface;
import by.zazybo.api.service.factory.ExpandFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DirectorServiceImpl implements Service {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DirectorDao directorDAO = daoFactory.getDirectorDAO();

    public String getById(int id) throws ServiceException {
        Gson gson = new Gson();

        try {
            Director directorById = directorDAO.getDirectorById(id);

            if (directorById == null) {
                return null;
            }

            DirectorResource directorResource = transformToDirectorResource(directorById, false);

            return gson.toJson(directorResource);
        } catch (DAOException e) {
            throw new DataBaseConnectionServiceException("DataBase connection problem.", e);
        }
    }

    public String getAll(Map<String, String> params) throws ServiceException {
        Gson gson = new Gson();
        ExpandFactory expandFactory = ExpandFactory.getInstance();
        ExpandInterface directorExpand = expandFactory.getDirectorExpand(params);

        List<Director> directors;
        List<DirectorResource> directorsResource = new ArrayList<>();
        try {
            directors = directorDAO.getDirectors();

            for (Director director : directors) {
                directorsResource.add(transformToDirectorResource(director, directorExpand.getExpand() != null));
            }

            return gson.toJson(directorsResource);
        } catch (DAOException e) {
            throw new DataBaseConnectionServiceException("DataBase connection problem.", e);
        }
    }


    private DirectorResource transformToDirectorResource(Director director, boolean isExpand) {

        DirectorResource directorResource = new DirectorResource(director.getId(), director.getFirstName(),
                director.getLastName(), director.getBirthDate(), true);
        if (director.getFilms().size() != 0) {
            for (Film film : director.getFilms()) {
                if (isExpand) {
                    directorResource.addFilm(getFilmExpand(film));
                } else {
                    directorResource.addFilm(getFilmNoExpand(film));
                }
            }
        }
        return directorResource;
    }

    private FilmResource getFilmNoExpand(Film film) {
        return new FilmResource(film.getId());
    }

    private FilmResource getFilmExpand(Film film) {
        return new FilmResource(film.getId(), film.getName(), film.getReleaseDate(), film.getGenre());
    }
}
