package by.zazybo.api.service.expand.impl;

import by.zazybo.api.service.expand.ExpandInterface;
import by.zazybo.api.service.expand.property.FilmExpandProperties;

import java.util.Map;

public class FilmExpand implements ExpandInterface {

    private String directors;

    public FilmExpand(Map<String, String> params) {

        if (params.containsKey("expand")) {
            if (params.get("expand").equals(FilmExpandProperties.DIRECTORS.toString().toLowerCase())) {
                this.directors = params.get("expand");
            }
        }

    }

    public String getExpand() {
        return directors;
    }
}
