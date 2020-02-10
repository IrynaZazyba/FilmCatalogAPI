package by.zazybo.api.runner;

import by.zazybo.api.config.ServerConfig;
import by.zazybo.api.handler.WrongRequestHandler;
import by.zazybo.api.config.RequestConfig;
import by.zazybo.api.handler.DirectorHandler;
import by.zazybo.api.handler.FilmHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Runner {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(ServerConfig.SERVER_PORT), 0);
        server.createContext("/", new WrongRequestHandler());
        server.createContext("/films", new FilmHandler());
        server.createContext("/directors", new DirectorHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("The server is running");


    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().add(RequestConfig.CONTENT_TYPE, RequestConfig.APPLICATION_JSON + ";" + RequestConfig.ENCODING);
        httpExchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }
}
