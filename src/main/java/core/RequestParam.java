package core;

import lombok.Getter;
import util.HttpHeaderUtils;

import java.util.Collections;
import java.util.Map;

@Getter
public class RequestParam {
    private Map<String, String> params;

    public static RequestParam create(String uri) {
        int index = uri.indexOf("?");

        if (index < 0) {
            return new RequestParam(Collections.emptyMap());
        }

        String queryString = uri.substring(index + 1);
        return new RequestParam(HttpHeaderUtils.parseKeyValue(queryString));
    }

    private RequestParam(Map<String, String> params) {
        this.params = params;
    }

    public String get(String key) {
        return params.get(key);
    }
}
