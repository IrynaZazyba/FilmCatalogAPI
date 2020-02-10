package by.zazybo.api.service.factory;

import by.zazybo.api.service.Service;
import by.zazybo.api.service.impl.DirectorServiceImpl;
import by.zazybo.api.service.impl.FilmServiceImpl;

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
