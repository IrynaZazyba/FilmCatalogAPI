package by.zazybo.domain.dao;

import by.zazybo.domain.bean.Film;
import by.zazybo.domain.dao.exception.DAOException;
import by.zazybo.domain.service.filter.FilmFilter;

import java.util.List;

public interface FilmDao {

    Film getFilmById(int id) throws DAOException;
    List<Film> getFilms(FilmFilter filter) throws DAOException;


}
