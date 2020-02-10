package by.zazybo.api.helper;

import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

public class HttpRequestParser {

    public static Map<String, String> splitQuery(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
                .map(str -> str.split("=")).collect(toMap(str -> str[0], str -> str[1]));
    }

    public static int getId(String path) throws NumberFormatException {
        String[] split = path.split("/");
        return Integer.parseInt(split[2]);
    }
}
