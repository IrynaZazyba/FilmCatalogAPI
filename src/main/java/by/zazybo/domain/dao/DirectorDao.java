package by.zazybo.domain.dao;

import by.zazybo.domain.bean.Director;
import by.zazybo.domain.dao.exception.DAOException;

import java.util.List;

public interface DirectorDao {

    Director getDirectorById(int director_id) throws DAOException;
    List<Director> getDirectors() throws DAOException;



}
