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


    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
