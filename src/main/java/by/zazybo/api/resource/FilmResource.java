package by.zazybo.api.resource;

import by.zazybo.api.config.ServerConfig;

import java.time.LocalDate;

public class FilmResource {

    private int id;
    private String name;
    private LocalDate releaseDate;
    private String genre;
    private DirectorResource director;
    private String rel;
    private String href;
    private String action;


    public FilmResource(int id) {
        this.id = id;
        this.rel = "films";
        this.href = "http://" + ServerConfig.IP_ADDRESS + ":" + ServerConfig.SERVER_PORT + "/films/" + id;
        this.action = "GET";
    }

    public FilmResource(int id, String name, LocalDate releaseDate, String genre) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public FilmResource(int id, String name, LocalDate releaseDate, String genre, DirectorResource director) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.director = director;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public DirectorResource getDirector() {
        return director;
    }

    public void setDirector(DirectorResource director) {
        this.director = director;
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
