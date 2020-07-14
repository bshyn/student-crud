package com.educacionit.student.api.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;


import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }

    @ResponseStatus(CONFLICT)
    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public ErrorMessage conflictRequest(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage badRequest(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler({
            UnauthorizedException.class,
            AccessDeniedException.class
    })
    @ResponseBody
    public ErrorMessage unauthorizedRequest(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            TransactionSystemException.class
    })
    @ResponseBody
    public ConstraintViolationErrorMessage handleTransactionSystem(Exception ex, HttpServletRequest request){
        Throwable cve = ex.getCause().getCause();
        ConstraintViolationErrorMessage CVEM = new ConstraintViolationErrorMessage(
                (ConstraintViolationException) cve, request.getRequestURI());
        return CVEM;
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorMessage fatalErrorUnexpectedException(Exception ex, HttpServletRequest request){
        return new ErrorMessage(ex, request.getRequestURI());
    }

}
