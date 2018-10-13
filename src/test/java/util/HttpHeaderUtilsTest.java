package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpHeaderUtilsTest {

    @Test
    public void getUrl() {
        // given
        String firstLine = "GET /index.html HTTP/1.1";

        // when
        String url = HttpHeaderUtils.getUrl(firstLine);

        // then
        assertEquals("/index.html", url);
    }
}