package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webserver.RequestHandler;

public class HttpHeaderUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    public static String getUrl(String firstLine) {
        String[] splittedFirstLine = firstLine.split(" ");
        if (splittedFirstLine.length != 3) {
            throw new IllegalStateException("잘못된 HTTP 헤더입니다.");
        }

        String url = splittedFirstLine[1];
        log.debug("Request Url={}", url);
        return url;
    }
}
