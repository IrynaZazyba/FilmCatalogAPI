package by.zazybo.api.handler;

import by.zazybo.api.runner.Runner;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;


public class WrongRequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(404,-1);
        Runner.writeResponse(exchange,"");
    }


}
