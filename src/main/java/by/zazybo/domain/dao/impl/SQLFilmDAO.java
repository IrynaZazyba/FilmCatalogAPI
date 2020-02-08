package by.zazybo.domain.dao.impl;


import by.zazybo.domain.bean.Director;
import by.zazybo.domain.bean.Film;
import by.zazybo.domain.connection.DBHandler;
import by.zazybo.domain.dao.FilmDao;
import by.zazybo.domain.dao.exception.DAOException;
import by.zazybo.domain.service.filter.FilmFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLFilmDAO implements FilmDao {


    DBHandler dbHandler = new DBHandler();


    public Film getFilmById(int id) throws DAOException {
        Film film = null;
        String sqlQueryFilmById = "SELECT f.id as film_id, f.name,f.release_date,f.genre,f.director_id,d.id as dir_id, d.first_name, d.last_name, d.birth_date FROM film f JOIN director d ON f.director_id=d.id WHERE f.id=?";

        try (Connection con = dbHandler.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(sqlQueryFilmById);) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                film = transformToEntity(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Didn't able to get information about film by id", e);
        }

        return film;
    }


    public List<Film> getFilms(FilmFilter filter) throws DAOException {

        List<Film> films = new ArrayList<>();
        String query = "SELECT f.id as film_id,d.first_name,d.last_name, d.birth_date, d.id as dir_id, f.name,f.genre,f.release_date, f.director_id FROM film f " +
                "INNER JOIN director d ON f.director_id=d.id";
        if (filter.getDirectorId() != null) {
            query = query + " WHERE f.director_id=" + filter.getDirectorId();
        }
        if (filter.getReleaseDate() != null && filter.getDirectorId() != null) {
            query = query + " AND f.release_date>=\'" + filter.getReleaseDate() + "\'";
        } else if (filter.getReleaseDate() != null) {
            query = query + " WHERE f.release_date>=\'" + filter.getReleaseDate() + "\'";
        }


        try (Connection con = dbHandler.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(query);) {
            // preparedStatement.setInt(1, director_id);
            //preparedStatement.setDate(2, java.sql.Date.valueOf(date));

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                    films.add(transformToEntity(resultSet));

            }
        } catch (SQLException e) {
            throw new DAOException("Didn't able to get information about film by director", e);
        }

        return films;

    }


    private Film transformToEntity(ResultSet resultSet) throws SQLException {

        int idFilm = resultSet.getInt("film_id");
        String name = resultSet.getString("name");
        LocalDate releaseDate = resultSet.getDate("release_date").toLocalDate();
        String genre = resultSet.getString("genre");
        return new Film(idFilm, name, releaseDate, genre, transformToFullEntity(resultSet));
    }

    private Director transformToFullEntity(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("dir_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        return new Director(id, firstName, lastName, birthDate);

    }
}
