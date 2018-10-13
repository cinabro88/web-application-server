package core;

import lombok.Getter;

import java.util.Map;

@Getter
public class RequestParam {
    private Map<String, String> params;

    public RequestParam(Map<String, String> params) {
        this.params = params;
    }

    public String get(String key) {
        return params.get(key);
    }
}
