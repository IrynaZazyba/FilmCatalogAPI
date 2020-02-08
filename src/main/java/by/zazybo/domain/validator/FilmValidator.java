package by.zazybo.domain.validator;


public class FilmValidator {

    public static boolean validateDate(String year) {
        return year.matches("\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isInteger(String id) {
        return id.matches("\\d*$");
    }


}
