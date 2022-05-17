package com.blue.wallet.controller.helper;

import com.blue.wallet.controller.transport.response.WebServiceErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBodyHelper {

    private ResponseBodyHelper() {}

    public static final ResponseEntity<?> notFound(String message) {
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.NOT_FOUND);
        WebServiceErrorResponse error = new WebServiceErrorResponse(message);
        return bodyBuilder.body(error);
    }

    public static final ResponseEntity<?> unprocessableEntity(String message) {
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY);
        WebServiceErrorResponse error = new WebServiceErrorResponse(message);
        return bodyBuilder.body(error);
    }

    public static final ResponseEntity<?> badRequest(String message) {
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        WebServiceErrorResponse error = new WebServiceErrorResponse(message);
        return bodyBuilder.body(error);
    }

    public static final ResponseEntity<?> internalServerError(String message) {
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        return bodyBuilder.body(message);
    }
}