package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestLineTest {

    @Test
    @DisplayName("ReqeustLine 생성")
    void create() {
        // given
        String firstRequestLine = "POST /users HTTP/1.1";

        // when
        RequestLine requestLine = RequestLine.create(firstRequestLine);

        // then
        assertEquals(HttpMethod.POST, requestLine.getHttpMethod());
        assertEquals("/users", requestLine.getPath());
    }

}