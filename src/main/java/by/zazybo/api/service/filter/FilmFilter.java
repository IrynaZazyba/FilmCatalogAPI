package by.zazybo.api.service.filter;


import by.zazybo.api.service.exception.InvalidDataServiceException;
import by.zazybo.api.service.exception.InvalidDateServiceException;
import by.zazybo.api.validator.FilmValidator;

import java.time.LocalDate;
import java.util.Map;

public class FilmFilter {

    private Integer directorId;
    private LocalDate dateStart;

    public FilmFilter(Map<String, String> params) throws InvalidDateServiceException,InvalidDataServiceException {
        if (params.containsKey(FilmFilterProperties.DIRECTOR_ID.toString().toLowerCase())) {
            String director_id = params.get(FilmFilterProperties.DIRECTOR_ID.toString().toLowerCase());
            if (!FilmValidator.isInteger(director_id)) {
                throw new InvalidDataServiceException("Invalid parameter.");
            }
            this.directorId = Integer.parseInt(director_id);

        }
        if (params.containsKey(FilmFilterProperties.DATE_START.toString().toLowerCase())) {
            String date_start = params.get(FilmFilterProperties.DATE_START.toString().toLowerCase());
            if (!FilmValidator.validateDate(date_start)) {
                throw new InvalidDateServiceException("Invalid parameter.");
            }
            this.dateStart = LocalDate.parse(date_start);
        }
    }

    public Integer getDirectorId() {
        return directorId;
    }


    public LocalDate getReleaseDate() {
        return dateStart;
    }


}
