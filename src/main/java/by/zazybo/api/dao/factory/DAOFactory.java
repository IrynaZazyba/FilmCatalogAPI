package by.zazybo.api.dao.factory;

import by.zazybo.api.dao.DirectorDao;
import by.zazybo.api.dao.FilmDao;
import by.zazybo.api.dao.impl.SQLDirectorDAO;
import by.zazybo.api.dao.impl.SQLFilmDAO;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final FilmDao SQLFilmImpl = new SQLFilmDAO();
    private final DirectorDao SQLDirectorImpl = new SQLDirectorDAO();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public FilmDao getFilmDAO() {
        return SQLFilmImpl;
    }

    public DirectorDao getDirectorDAO() {
        return SQLDirectorImpl;
    }
}
