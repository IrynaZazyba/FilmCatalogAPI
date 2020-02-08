package by.zazybo.domain.dao.impl;

import by.zazybo.domain.bean.Director;
import by.zazybo.domain.bean.Film;
import by.zazybo.domain.connection.DBHandler;
import by.zazybo.domain.dao.DirectorDao;
import by.zazybo.domain.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SQLDirectorDAO implements DirectorDao {

    DBHandler dbHandler = new DBHandler();

    public Director getDirectorById(int director_id) throws DAOException {
        Director director = null;
        String sqlQueryDirectorById = "SELECT f.id as film_id, d.id as dir_id, d.first_name,d.last_name,d.birth_date, f.name, f.release_date, f.genre, f.director_id FROM director d\n" +
                "LEFT JOIN film f ON d.id=f.director_id";

        try (Connection con = dbHandler.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sqlQueryDirectorById);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                if (resultSet.getInt("dir_id") == director_id && director == null) {
                    director = transformToEntity(resultSet);
                    if (resultSet.getObject("film_id") != null) {
                        director.addFilm(transformToFullEntity(resultSet));
                    }
                } else if (director != null && resultSet.getInt("dir_id") == director_id) {
                    director.addFilm(transformToFullEntity(resultSet));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("Didn't able to get information about director by id", e);
        }
        return director;
    }


    public List<Director> getDirectors() throws DAOException {
        List<Director> directors = new ArrayList<>();
        Director director;
        String sqlQueryDirectorById = "SELECT f.id as film_id, d.id as dir_id, d.first_name,d.last_name,d.birth_date, f.name, f.release_date, f.genre, f.director_id FROM director d\n" +
                "LEFT JOIN film f ON d.id=f.director_id";
        try (Connection con = dbHandler.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sqlQueryDirectorById);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                director = transformToEntity(resultSet);
                if (!directors.contains(director)) {
                    if (resultSet.getObject("film_id") != null) {

                            director.addFilm(transformToFullEntity(resultSet));

                    }
                    directors.add(director);
                } else {
                    for (Director d : directors) {
                        if (d.getId() == resultSet.getInt("director_id")) {
                                d.addFilm(transformToFullEntity(resultSet));

                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Didn't able to get information about director by id", e);
        }
        return directors;
    }


    private Director transformToEntity(ResultSet resultSet) throws SQLException {

        int director_id = resultSet.getInt("dir_id");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        LocalDate releaseDate = resultSet.getDate("birth_date").toLocalDate();
        return new Director(director_id, first_name, last_name, releaseDate);
    }

    private Film transformToFullEntity(ResultSet resultSet) throws SQLException {
        int film_id = resultSet.getInt("film_id");
        String film_name = resultSet.getString("name");
        String genre = resultSet.getString("genre");
        LocalDate release_date = resultSet.getDate("release_date").toLocalDate();
        return new Film(film_id, film_name, release_date, genre);

    }
}

