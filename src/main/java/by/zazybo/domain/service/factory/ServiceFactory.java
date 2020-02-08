package by.zazybo.domain.service.factory;

import by.zazybo.domain.service.Service;
import by.zazybo.domain.service.impl.DirectorServiceImpl;
import by.zazybo.domain.service.impl.FilmServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final Service filmService = new FilmServiceImpl();
    private final Service directorService = new DirectorServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Service getFilmService() {
        return filmService;
    }

    public Service getDirectorService() {
        return directorService;
    }
}
