package helper;

import by.zazybo.domain.helper.HttpRequestParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpRequestParserTest {

    @Test
    public void splitQuery_returnMapParams() {
        String query = "expand=directors&date_start=1990-01-01";
        Map<String, String> actualParams = HttpRequestParser.splitQuery(query);
        Map<String, String> expectedParams = new HashMap<>();
        expectedParams.put("expand", "directors");
        expectedParams.put("date_start", "1990-01-01");
        Assert.assertEquals(expectedParams, actualParams);

    }

    @Test
    public void splitQuery_returnNullMapParams() {
        String query = "";
        Map<String, String> actualParams = HttpRequestParser.splitQuery(query);
        Map<String, String> expectedParams = new HashMap<>();
        Assert.assertEquals(expectedParams, actualParams);
        String queryNull=null;
        Map<String, String> actualParams1 = HttpRequestParser.splitQuery(query);
        Map<String, String> expectedParams1 = new HashMap<>();
        Assert.assertEquals(expectedParams1, actualParams1);
    }

    @Test
    public void getId_returnId() throws NumberFormatException {
        String path = "/films/1";

        int actualId = HttpRequestParser.getId(path);
        int expectedId = 1;
        Assert.assertEquals(expectedId, actualId);
    }

   @Test(expected = NumberFormatException.class)
    public void getId_throwsException() throws NumberFormatException {
        String path = "/films/1y";
        HttpRequestParser.getId(path);

    }



}
