package by.zazybo.api.validator;

import org.junit.Assert;
import org.junit.Test;

public class FilmValidatorTest {


    @Test
    public void validateDateTest_returnTrue() {
        String year = "2019-01-03";
        Assert.assertTrue(FilmValidator.validateDate(year));
    }

    @Test
    public void validateDateTest_returnFalse() {
        String year = "01-03-2019";
        Assert.assertFalse(FilmValidator.validateDate(year));
        String year1 = "year";
        Assert.assertFalse(FilmValidator.validateDate(year1));
        String year2 = "2019";
        Assert.assertFalse(FilmValidator.validateDate(year2));
    }


    @Test
    public void isInteger_returnTrue() {
        String number = "17";
        Assert.assertTrue(FilmValidator.isInteger(number));
    }

    @Test
    public void isInteger_returnFalse() {
        String number = "17j";
        Assert.assertFalse(FilmValidator.isInteger(number));
    }


}
