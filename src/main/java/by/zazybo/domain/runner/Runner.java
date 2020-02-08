package by.zazybo.domain.runner;

import by.zazybo.domain.helper.Config;
import by.zazybo.domain.controller.DirectorHandler;
import by.zazybo.domain.controller.FilmHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Runner {

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(Config.SERVER_PORT), 0);
        server.createContext("/films", new FilmHandler());
        server.createContext("/directors", new DirectorHandler());

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("The server is running");


    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().add(Config.CONTENT_TYPE, Config.APPLICATION_JSON + ";" + Config.ENCODING);
        httpExchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }
}
