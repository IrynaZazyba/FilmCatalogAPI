package by.zazybo.api.resource;

import by.zazybo.api.config.ServerConfig;

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
    private String href;
    private String action;


    public DirectorResource(int id) {
        this.id = id;
        this.rel = "directors";
        this.action = "GET";
        this.href = "http://" + ServerConfig.IP_ADDRESS + ":" + ServerConfig.SERVER_PORT + "/directors/" + id;
    }


    public DirectorResource(int id, String firstName, String lastName, LocalDate date, boolean isExpand) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = date;
        if (isExpand) {
            films = new ArrayList<>();
        }

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
        DirectorResource director = (DirectorResource) obj;
        if (this.id != director.id) return false;
        if (director.firstName == null) {
            if (this.firstName != null) return false;
        } else {
            if (!director.firstName.equals(this.firstName)) return false;
        }
        if (director.lastName == null) {
            if (this.lastName != null) return false;
        } else {
            if (!director.lastName.equals(this.lastName)) return false;
        }
        if (director.birthDate == null) {
            if (this.birthDate != null) return false;
        } else {
            if (!director.birthDate.equals(this.birthDate)) return false;
        }
        if (director.films == null) {
            if (this.films != null) return false;
        } else {
            if (!director.films.equals(this.films)) return false;
        }
        if (director.rel == null) {
            if (this.rel != null) return false;
        } else {
            if (!director.rel.equals(this.rel)) return false;
        }
        if (director.href == null) {
            if (this.href != null) return false;
        } else {
            if (!director.href.equals(this.href)) return false;
        }
        if (director.action == null) {
            if (this.action != null) return false;
        } else {
            if (!director.action.equals(this.action)) return false;
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
        result = prime * result + (rel == null ? 0 : rel.hashCode());
        result = prime * result + (href == null ? 0 : href.hashCode());
        result = prime * result + (action == null ? 0 : action.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder directorFilms = new StringBuilder();
        films.forEach(film -> directorFilms.append(film).append(", "));
        return getClass().getName() + '@' +
                "id=" + id +
                ", firstName='" + firstName +
                ", lastName='" + lastName +
                ", birthDate=" + birthDate +
                ", films=" + directorFilms +
                ", rel=" + rel +
                ", href=" + href +
                ", action=" + action;
    }


}
