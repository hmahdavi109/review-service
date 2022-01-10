package mahdavi.example.review.utils;

import lombok.Data;

import java.util.Date;

@Data
public class RestResponse<T> {

    private long timestamp;
    private String message;
    private T data;

    private RestResponse() {
    }

    public static RestResponse<Void> ok() {
        return ok(null);
    }

    public static <T> RestResponse<T> ok(T content) {
        RestResponse<T> response = new RestResponse<>();
        response.timestamp = new Date().getTime();
        response.data = content;
        response.message = "OK";
        return response;
    }

    public static <T> RestResponse<T> error(String message) {
        RestResponse<T> response = new RestResponse<>();
        response.timestamp = new Date().getTime();
        response.data = null;
        response.message = message;
        return response;
    }

    public static <T> RestResponse<T> error(String message, T content) {
        RestResponse<T> response = new RestResponse<>();
        response.timestamp = new Date().getTime();
        response.data = content;
        response.message = message;
        return response;
    }

    public T getData() {
        return this.data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return this.message;
    }
}
