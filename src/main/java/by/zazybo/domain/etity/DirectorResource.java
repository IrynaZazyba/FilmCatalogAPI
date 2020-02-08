package by.zazybo.domain.etity;

import by.zazybo.domain.helper.Config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DirectorResource {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private List<FilmResource> films;
    private String rel;
    private String action;
    private String href;

    public DirectorResource(int id) {
        this.id = id;
        this.rel = "directors";
        this.action = "GET";
        this.href = "http://" + Config.IP_ADDRESS + ":" + Config.SERVER_PORT + "/directors/" + id;
    }


    public DirectorResource(int id, String firstName, String lastName, LocalDate date, boolean isExpand) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = date;
        if(isExpand){
        films = new ArrayList<>();}

            }

    public void setDirectorLink(int id) {
        this.id = id;
        this.rel = "directors";
        this.action = "GET";
        this.href = "http://" + Config.IP_ADDRESS + ":" + Config.SERVER_PORT + "/directors/" + id;
    }

    public List getFilms() {
        return Collections.unmodifiableList(films);
    }

    public void addFilm(FilmResource film) {
        if (films == null) {
            films = new ArrayList<>();
        }
        films.add(film);
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
        DirectorResource film = (DirectorResource) obj;
        if (this.id != film.id) return false;
        if (film.firstName != null) {
            if (this.firstName == null) return false;
        } else {
            if (!film.firstName.equals(this.firstName)) return false;
        }
        if (film.lastName != null) {
            if (this.lastName == null) return false;
        } else {
            if (!film.lastName.equals(this.lastName)) return false;
        }
        if (film.birthDate != null) {
            if (this.birthDate == null) return false;
        } else {
            if (!film.birthDate.equals(this.birthDate)) return false;
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
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + '@' +
                "id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", birthDate=" + birthDate;
    }


}
