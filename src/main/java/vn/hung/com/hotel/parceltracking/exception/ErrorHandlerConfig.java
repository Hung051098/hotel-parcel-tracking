package vn.hung.com.hotel.parceltracking.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.hung.com.hotel.parceltracking.model.response.Response;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> exception(Exception ex) {
        log.error("Exception: {}", ex.getMessage());

        Response.Error error = new Response.Error();

        error.setErrorMessage("Có lỗi xảy ra, vui lòng thử lại sau");
        Response response = Response.builder()
                .error(error)
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Response> validationEx(ValidationException ex) throws JsonProcessingException {
        log.error("ValidationException: {}", ex.getMessage());
        Response.Error error = new Response.Error();
        error.setErrorMessage(ex.getLocalizedMessage());
        Response response = Response.builder()
                .error(error)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
