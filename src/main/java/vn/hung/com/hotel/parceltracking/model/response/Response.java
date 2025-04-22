package vn.hung.com.hotel.parceltracking.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Response {

    Object data;
    Error error;
    public Response() {
    }

    public Response(Object data, Error error) {
        this.data = data;
        this.error = error;
    }

    @Getter
    @Setter
    public static class Error {
        String errorMessage;
        Object data;
    }
}
