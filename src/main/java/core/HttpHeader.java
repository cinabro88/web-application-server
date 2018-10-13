package core;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class HttpHeader {
    private HttpMethod httpMethod;
    private String path;
    private RequestParam requestParam;

    @Builder
    public HttpHeader(HttpMethod httpMethod, String path, RequestParam requestParam) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.requestParam = requestParam;
    }
}
