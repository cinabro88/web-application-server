package core;

import lombok.Builder;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class RequestLine {
    private static final Logger log = LoggerFactory.getLogger(RequestLine.class);

    private HttpMethod httpMethod;
    private String path;
    private RequestParam requestParam;

    @Builder
    private RequestLine(HttpMethod httpMethod, String path, RequestParam requestParam) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.requestParam = requestParam;
    }

    public static RequestLine create(String firstRequestLine) {
        String[] lineToken = firstRequestLine.split(" ");
        if (lineToken.length != 3) {
            log.error("RequestLine 생성 오류. firstRequestLine={}", firstRequestLine);
            throw new IllegalArgumentException("HTTP Request 의 첫번째 라인의 형식이 잘못되었습니다.");
        }

        String uri = lineToken[1];

        return builder()
                .httpMethod(HttpMethod.valueOf(lineToken[0]))
                .path(createPath(uri))
                .requestParam(RequestParam.create(uri))
                .build();

    }

    private static String createPath(String uri) {
        int index = uri.indexOf("?");
        return index < 0 ? uri : uri.substring(0, index);
    }
}
