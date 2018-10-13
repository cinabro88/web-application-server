package util;

import core.HttpHeader;
import core.HttpMethod;
import core.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class HttpHeaderUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    public static HttpHeader parseFirstLine(String firstLine) {
        String[] splitFirstLine = firstLine.split(" ");
        if (splitFirstLine.length != 3) {
            throw new IllegalStateException("잘못된 HTTP 헤더입니다.");
        }

        return HttpHeader.builder()
                .httpMethod(HttpMethod.valueOf(splitFirstLine[0]))
                .path(getPath(splitFirstLine[1]))
                .requestParam(getRequestParam(splitFirstLine[1]))
                .build();
    }

    public static String getUrl(String firstLine) {
        String[] splittedFirstLine = firstLine.split(" ");
        String url = splittedFirstLine[1];
        log.debug("Request Url={}", url);
        return url;
    }

    public static String getPath(String url) {
        int index = url.indexOf("?");
        return index < 0 ? url : url.substring(0, index);
    }

    public static RequestParam getRequestParam(String url) {
        int index = url.indexOf("?");
        String queryString = url.substring(index + 1);
        String[] params = queryString.split("&");

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
        return new RequestParam(paramsMap);
    }

}
