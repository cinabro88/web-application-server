package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class HttpHeaderUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    public static String getUrl(String firstLine) {
        String[] splittedFirstLine = firstLine.split(" ");
        String url = splittedFirstLine[1];
        log.debug("Request Url={}", url);
        return url;
    }


    public static Map<String, String> parseKeyValue(String keyValueString) {
        String[] params = keyValueString.split("&");
        Map<String, String> paramsMap = new HashMap<>();
        for (String param : params) {
            String[] splitParam = param.split("=");

            String value;
            if (splitParam.length < 2) {
                value = "";
            } else {
                value = splitParam[1];
            }

            paramsMap.put(splitParam[0], value);
        }
        return paramsMap;
    }

}
