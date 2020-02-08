package by.zazybo.domain.etity;

import by.zazybo.domain.helper.Config;

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
        this.href = "http://" + Config.IP_ADDRESS + ":" + Config.SERVER_PORT + "/films/" + id;
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

    public void setFilmLink(int id) {
        this.id = id;
        this.rel = "films";
        this.href = "http://" + Config.IP_ADDRESS + ":" + Config.SERVER_PORT + "/films/" + id;
        this.action = "GET";
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        FilmResource director = (FilmResource) obj;
        if (director.id != this.id) return false;
        if (director.name != null) {
            if (this.name == null) return false;
        } else {
            if (!director.name.equals(this.name)) return false;
        }
        if (director.releaseDate != null) {
            if (this.releaseDate == null) return false;
        } else {
            if (!director.releaseDate.equals(this.releaseDate)) return false;
        }
        if (director.genre != null) {
            if (this.genre == null) return false;
        } else {
            if (!director.genre.equals(this.genre)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = result * prime + id;
        result = result * prime + (name == null ? 0 : name.hashCode());
        result = result * prime + (releaseDate == null ? 0 : releaseDate.hashCode());
        result = result * prime + (genre == null ? 0 : genre.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "id=" + id +
                ", name='" + name + '/' +
                ", releaseDate=" + releaseDate +
                ", genre='" + genre;
    }

    public DirectorResource getDirector() {
        return director;
    }

    public void setDirector(DirectorResource director) {
        this.director = director;
    }
}
