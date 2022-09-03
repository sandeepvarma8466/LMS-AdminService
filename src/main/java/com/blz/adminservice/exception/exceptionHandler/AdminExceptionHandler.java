package com.blz.adminservice.exception.exceptionHandler;

import com.blz.adminservice.exception.AdminNotFoundException;
import com.blz.adminservice.util.AdminResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AdminExceptionHandler {
    @ExceptionHandler(AdminNotFoundException.class)
    public ResponseEntity<AdminResponse> response(AdminNotFoundException adminNotFoundException) {
        AdminResponse adminResponce = new AdminResponse();
        adminResponce.setErrorcode(400);
        adminResponce.setMessage(adminNotFoundException.getMessage());
        return new ResponseEntity<>(adminResponce, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<AdminResponse>
    defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        AdminResponse adminResponce = new AdminResponse();
        adminResponce.setErrorcode(500);
        adminResponce.setMessage(e.getMessage());
        return new ResponseEntity<AdminResponse>(adminResponce, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
