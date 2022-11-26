package az.unibank.unitech.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeadersHelper {

    private HeadersHelper() {
    }

    public static HttpHeaders addHeader(String key, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(key, value);
        return headers;
    }

    public static <T> HttpEntity generateEntityWithHeader(String key, String value, T entity) {
        return new HttpEntity<>(entity, addHeader(key, value));
    }

    public static <T> HttpEntity generateJsonEntity(T entity) {

        return new HttpEntity<>(entity, generateJsonHeader());
    }

    public static HttpHeaders generateJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

}
