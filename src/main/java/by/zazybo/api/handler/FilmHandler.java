package by.zazybo.api.handler;

import by.zazybo.api.helper.HttpRequestParser;
import by.zazybo.api.runner.Runner;
import by.zazybo.api.service.Service;
import by.zazybo.api.service.exception.InvalidDataServiceException;
import by.zazybo.api.service.exception.InvalidDateServiceException;
import by.zazybo.api.service.exception.ServiceException;
import by.zazybo.api.service.factory.ServiceFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;


public class FilmHandler implements HttpHandler {

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private Service filmService = serviceFactory.getFilmService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
        }

        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String response=null;

        if (path.matches("/films")) {
            try {
                response = filmService.getAll(HttpRequestParser.splitQuery(requestURI.getQuery()));
            } catch (ServiceException e) {

                if (e instanceof InvalidDateServiceException || e instanceof InvalidDataServiceException) {
                    exchange.sendResponseHeaders(409, -1);
                } else {
                    exchange.sendResponseHeaders(500, -1);
                }
            }
        } else if (path.matches("/films/\\d*")) {
            try {
                response = filmService.getById(HttpRequestParser.getId(path));
                if (response == null) {
                    exchange.sendResponseHeaders(404, -1);
                }
            } catch (ServiceException e) {
                exchange.sendResponseHeaders(500, -1);
            }
        } else {
            exchange.sendResponseHeaders(404, -1);

        }
        Runner.writeResponse(exchange, response);
    }


}
