package by.zazybo.api.service.expand.impl;

import by.zazybo.api.service.expand.property.DirectorExpandProperties;
import by.zazybo.api.service.expand.ExpandInterface;

import java.util.Map;

public class DirectorExpand implements ExpandInterface {

    private String films;

    public DirectorExpand(Map<String, String> params) {

        if (params.containsKey("expand")) {
            if (params.get("expand").equals(DirectorExpandProperties.FILMS.toString().toLowerCase())) {
                this.films = params.get("expand");
            }
        }
    }

    public String getExpand() {
        return films;
    }
}
