package by.zazybo.api.dao;

import by.zazybo.api.bean.Film;
import by.zazybo.api.dao.exception.DAOException;
import by.zazybo.api.service.filter.FilmFilter;

import java.util.List;

public interface FilmDao {

    Film getFilmById(int id) throws DAOException;
    List<Film> getFilms(FilmFilter filter) throws DAOException;
}
