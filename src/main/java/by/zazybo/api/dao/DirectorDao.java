package by.zazybo.api.dao;

import by.zazybo.api.bean.Director;
import by.zazybo.api.dao.exception.DAOException;

import java.util.List;

public interface DirectorDao {

    Director getDirectorById(int director_id) throws DAOException;
    List<Director> getDirectors() throws DAOException;
}
