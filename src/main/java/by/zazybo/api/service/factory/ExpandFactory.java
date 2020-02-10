package by.zazybo.api.service.factory;

import by.zazybo.api.service.expand.impl.DirectorExpand;
import by.zazybo.api.service.expand.impl.FilmExpand;

import java.util.Map;

public final class ExpandFactory {

    private static final ExpandFactory instance = new ExpandFactory();

    private ExpandFactory() {
    }

    public static ExpandFactory getInstance() {
        return instance;
    }

    public FilmExpand getFilmExpand(Map<String, String> params) {
        return new FilmExpand(params);
    }

    public DirectorExpand getDirectorExpand(Map<String, String> params) {
        return new DirectorExpand(params);

    }
}
