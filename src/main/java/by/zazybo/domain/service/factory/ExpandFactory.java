package by.zazybo.domain.service.factory;

import by.zazybo.domain.service.expand.impl.DirectorExpand;
import by.zazybo.domain.service.expand.impl.FilmExpand;

import java.util.Map;

public final class ExpandFactory {

    private static final ExpandFactory instance = new ExpandFactory();

    private FilmExpand filmExpand;
    private DirectorExpand directorExpand;

    private ExpandFactory() {
    }

    public static ExpandFactory getInstance() {
        return instance;
    }

    public FilmExpand getFilmExpand(Map<String, String> params) {

            filmExpand = new FilmExpand(params);

        return filmExpand;
    }

    public DirectorExpand getDirectorExpand(Map<String, String> params) {
            directorExpand = new DirectorExpand(params);

        return directorExpand;
    }

    public void getFilmExpand() {
    }
}
