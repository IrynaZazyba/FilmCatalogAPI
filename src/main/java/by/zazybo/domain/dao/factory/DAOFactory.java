package by.zazybo.domain.dao.factory;

import by.zazybo.domain.dao.DirectorDao;
import by.zazybo.domain.dao.FilmDao;
import by.zazybo.domain.dao.impl.SQLDirectorDAO;
import by.zazybo.domain.dao.impl.SQLFilmDAO;

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
