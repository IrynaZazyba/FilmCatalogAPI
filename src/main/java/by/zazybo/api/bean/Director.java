package by.zazybo.api.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Director implements Serializable {

    private static final long serialVersionUID = 5672027191025569058L;

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<Film> films = new ArrayList<>();

    public Director() {
    }

    public Director(Director director) {
        this.id = director.getId();
        this.firstName = director.getFirstName();
        this.lastName = director.getLastName();
        this.birthDate = LocalDate.of(director.getBirthDate().getYear(), director.getBirthDate().getMonthValue(),
                director.getBirthDate().getDayOfMonth());
        this.films.addAll(director.getFilms());
        this.films = director.getFilms();
    }


    public Director(int id, String firstName, String lastName, LocalDate date) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = date;

    }

    public void setFilms(Film film) {
        films.add(film);
    }

    public List<Film> getFilms() {
        return Collections.unmodifiableList(films);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Director film = (Director) obj;
        if (this.id != film.id) return false;
        if (film.firstName == null) {
            if (this.firstName != null) return false;
        } else {
            if (!film.firstName.equals(this.firstName)) return false;
        }
        if (film.lastName == null) {
            if (this.lastName != null) return false;
        } else {
            if (!film.lastName.equals(this.lastName)) return false;
        }
        if (film.birthDate == null) {
            if (this.birthDate != null) return false;
        } else {
            if (!film.birthDate.equals(this.birthDate)) return false;
        }
        if (film.films== null) {
            if (this.films != null) return false;
        } else {
            if (!film.films.equals(this.films)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (firstName == null ? 0 : firstName.hashCode());
        result = prime * result + (lastName == null ? 0 : lastName.hashCode());
        result = prime * result + (birthDate == null ? 0 : birthDate.hashCode());
        result = prime * result + (films == null ? 0 : films.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder directorFilms=new StringBuilder();
        films.forEach(film->directorFilms.append(film).append(", "));
        return getClass().getName() + '@' +
                "id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", birthDate=" + birthDate+
                ", films="+directorFilms.toString().trim();
    }



}
