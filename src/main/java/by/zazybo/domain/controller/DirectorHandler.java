package by.zazybo.domain.controller;

import by.zazybo.domain.helper.HttpRequestParser;
import by.zazybo.domain.runner.Runner;
import by.zazybo.domain.service.Service;
import by.zazybo.domain.service.exception.ServiceException;
import by.zazybo.domain.service.factory.ServiceFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.net.URI;


public class DirectorHandler implements HttpHandler {


    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private Service directorService = serviceFactory.getDirectorService();


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (!"GET".equals(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
        }

        URI requestURI = exchange.getRequestURI();
        String path = requestURI.getPath();
        String response = null;

        if (path.matches("/directors")) {
            try {
                response = directorService.getAll(HttpRequestParser.splitQuery(requestURI.getQuery()));
            } catch (ServiceException e) {
                exchange.sendResponseHeaders(500, -1);
            }
        }


        if (path.matches("/directors/\\d*")) {
            try {
                response = directorService.getById(HttpRequestParser.getId(path));
                if (response == null) {
                    exchange.sendResponseHeaders(404, -1);
                }
            } catch (ServiceException e) {
                exchange.sendResponseHeaders(500, -1);
            }
        }

        if (path.matches("/films/([a-zA-Z].*|[а-яА-Я].*|\\W*)")) {
            exchange.sendResponseHeaders(404, -1);

        }
        Runner.writeResponse(exchange, response);

    }


}
