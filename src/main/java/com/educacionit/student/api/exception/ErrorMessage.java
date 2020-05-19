package com.educacionit.student.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorMessage {

    private String exception;

    private String message;

    private String path;

    public ErrorMessage(Exception ex, String path){
        this.exception = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        this.path = path;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "exception='" + exception + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
