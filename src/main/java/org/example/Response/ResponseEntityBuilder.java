package org.example.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseEntityBuilder {

    public static <T> ResponseEntity<ResponseEntityDto<T>> buildResponse(HttpStatus status, String msg, T data) {
        ResponseEntityDto<T> responseBody = new ResponseEntityDto<>(status.name(), msg, data);
        return new ResponseEntity<>(responseBody, status);
    }

    public static <T> ResponseEntity<ResponseEntityDto<T>> okResponse(T data) {
        String msg = "SUCCESS";
        return buildResponse(HttpStatus.OK, msg, data);
    }

    public static <T> ResponseEntity<ResponseEntityDto<T>> createdResponse(T data) {
        String msg = "SUCCESS";
        return buildResponse(HttpStatus.CREATED, msg, data);
    }

    public static <T> ResponseEntity<ResponseEntityDto<T>> badRequestResponse(String msg, T data) {
        return buildResponse(HttpStatus.BAD_REQUEST, msg, data);
    }

    public static <T> ResponseEntity<ResponseEntityDto<T>> errorResponse(String msg) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, msg, null);
    }

    public static <T> ResponseEntity<ResponseEntityDto<T>> notFoundResponse(String msg, T data) {
        return buildResponse(HttpStatus.NOT_FOUND, msg, data);
    }

}
