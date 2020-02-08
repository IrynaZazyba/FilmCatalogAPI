package by.zazybo.domain.service.impl;

import by.zazybo.domain.bean.Director;
import by.zazybo.domain.bean.Film;
import by.zazybo.domain.dao.DirectorDao;
import by.zazybo.domain.dao.exception.DAOException;
import by.zazybo.domain.dao.factory.DAOFactory;
import by.zazybo.domain.etity.DirectorResource;
import by.zazybo.domain.etity.FilmResource;
import by.zazybo.domain.service.Service;
import by.zazybo.domain.service.exception.ServiceException;
import by.zazybo.domain.service.expand.ExpandInterface;
import by.zazybo.domain.service.factory.ExpandFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DirectorServiceImpl implements Service {

    private DAOFactory daoFactory = DAOFactory.getInstance();
    private DirectorDao directorDAO = daoFactory.getDirectorDAO();

    private Gson gson = new Gson();


    public String getById(int id) throws ServiceException {
        try {
            Director directorById = directorDAO.getDirectorById(id);

            if (directorById == null) {
                return null;
            }

            DirectorResource directorResource = transformToDirectorResource(directorById, false);

            return gson.toJson(directorResource);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    public String getAll(Map<String, String> params) throws ServiceException {

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
            throw new ServiceException(e);
        }
    }


    private DirectorResource transformToDirectorResource(Director director, boolean isExpand) {

        DirectorResource directorResource = new DirectorResource(director.getId(), director.getFirstName(),
                director.getLastName(), director.getBirthDate(), true);
        if (director.getFilms().size() == 0) {

        } else {
            for (Object film : director.getFilms()) {
                Film f = ((Film) film);

                if (isExpand) {
                    directorResource.addFilm(getFilmExpand(f));
                } else {
                    directorResource.addFilm(getFilmNoExpand(f));
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
