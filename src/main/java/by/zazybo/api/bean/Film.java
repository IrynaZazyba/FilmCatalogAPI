package by.zazybo.api.bean;

import java.io.Serializable;
import java.time.LocalDate;

public class Film implements Serializable {

    private static final long serialVersionUID = -7236930998696476378L;

    private int id;
    private String name;
    private LocalDate releaseDate;
    private String genre;
    private Director director;

    public Film() {
    }

    public Film(int id, String name, LocalDate releaseDate, String genre) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Film(int id, String name, LocalDate releaseDate, String genre, Director director) {
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


    public Director getDirector() {
        return new Director(director);
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Film director = (Film) obj;
        if (director.id != this.id) return false;
        if (director.name == null) {
            if (this.name != null) return false;
        } else {
            if (!director.name.equals(this.name)) return false;
        }
        if (director.releaseDate == null) {
            if (this.releaseDate != null) return false;
        } else {
            if (!director.releaseDate.equals(this.releaseDate)) return false;
        }
        if (director.genre == null) {
            if (this.genre != null) return false;
        } else {
            if (!director.genre.equals(this.genre)) return false;
        }
        if (director.director == null) {
            if (this.director != null) return false;
        } else {
            if (!director.director.equals(this.director)) return false;
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
        result = result * prime + (director == null ? 0 : director.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "id=" + id +
                ", name=" + name +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                ", director=" + director;
    }


}
