package util;

import core.RequestParam;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void getPath() {
        // given
        String url = "/user/form?id=id&password=password&name=name&empty=";

        // when
        String path = HttpHeaderUtils.getPath(url);

        // then
        assertEquals("/user/form", path);
    }

    @Test
    public void getQueryParams() {
        // given
        String url = "/user/form?id=id&password=password&name=name&empty=";

        // when
        RequestParam params = HttpHeaderUtils.getRequestParam(url);

        // then
        assertEquals("id", params.get("id"));
        assertEquals("password", params.get("password"));
        assertEquals("name", params.get("name"));
        assertEquals("", params.get("empty"));
    }
}