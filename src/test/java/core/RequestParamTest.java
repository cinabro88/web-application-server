package core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestParamTest {

    @Test
    @DisplayName("uri 에 쿼리스트링이 있는 경우")
    void create() {
        // given
        String uri = "/users?id=cinabro88&password=1234&name=";

        // when
        RequestParam requestParam = RequestParam.create(uri);

        // then
        assertEquals("cinabro88", requestParam.get("id"));
        assertEquals("1234", requestParam.get("password"));
        assertEquals("", requestParam.get("name"));

    }

    @Test
    @DisplayName("uri 에 쿼리스트링이 없는 경우")
    void create2() {
        // given
        String uri = "/users";

        // when
        RequestParam requestParam = RequestParam.create(uri);

        // then
        assertNull(requestParam.get("id"));
    }
}