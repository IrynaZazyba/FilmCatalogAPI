package by.zazybo.api.dao.impl;


import by.zazybo.api.bean.Director;
import by.zazybo.api.bean.Film;
import by.zazybo.api.connection.ConnectionDBHandler;
import by.zazybo.api.dao.FilmDao;
import by.zazybo.api.dao.exception.DAOException;
import by.zazybo.api.service.filter.FilmFilter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLFilmDAO implements FilmDao {


    private ConnectionDBHandler dbHandler = new ConnectionDBHandler();


    public Film getFilmById(int id) throws DAOException {
        Film film = null;
        String sqlQueryFilmById = "SELECT f.id as film_id, f.name,f.release_date,f.genre,f.director_id,d.id as dir_id," +
                " d.first_name, d.last_name, d.birth_date FROM film f JOIN director d ON f.director_id=d.id WHERE f.id=?";

        try (Connection con = dbHandler.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(sqlQueryFilmById)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                film = transformToFilm(resultSet);
            }

        } catch (SQLException e) {
            throw new DAOException("Didn't able to get information about film by id", e);
        }

        return film;
    }


    public List<Film> getFilms(FilmFilter filter) throws DAOException {

        List<Film> films = new ArrayList<>();
        String query = "SELECT f.id as film_id,d.first_name,d.last_name, d.birth_date, d.id as dir_id, f.name,f.genre," +
                "f.release_date, f.director_id FROM film f INNER JOIN director d ON f.director_id=d.id";
        if (filter.getDirectorId() != null) {
            query = query + " WHERE f.director_id=?";
        }
        if (filter.getReleaseDate() != null && filter.getDirectorId() != null) {
            query = query + " AND f.release_date>=?";
        } else if (filter.getReleaseDate() != null) {
            query = query + " WHERE f.release_date>=?";
        }

        try (Connection con = dbHandler.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(query)) {

            if (filter.getDirectorId() != null) {
                preparedStatement.setInt(1, filter.getDirectorId());
            }

            if (filter.getReleaseDate() != null && filter.getDirectorId() != null) {
                preparedStatement.setObject(2, filter.getReleaseDate());

            } else if (filter.getReleaseDate() != null) {
                preparedStatement.setObject(1, filter.getReleaseDate());
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                films.add(transformToFilm(resultSet));

            }
        } catch (SQLException e) {
            throw new DAOException("Didn't able to get information about film by director", e);
        }

        return films;

    }


    private Film transformToFilm(ResultSet resultSet) throws SQLException {

        int idFilm = resultSet.getInt("film_id");
        String name = resultSet.getString("name");
        LocalDate releaseDate = resultSet.getDate("release_date").toLocalDate();
        String genre = resultSet.getString("genre");
        return new Film(idFilm, name, releaseDate, genre, transformToDirector(resultSet));
    }

    private Director transformToDirector(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("dir_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        LocalDate birthDate = resultSet.getDate("birth_date").toLocalDate();
        return new Director(id, firstName, lastName, birthDate);

    }
}
