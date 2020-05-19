package com.educacionit.student.api.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintViolationErrorMessage {

    private String exception;
    private List<String> constraintsViolations;
    private String path;

    public ConstraintViolationErrorMessage(ConstraintViolationException ex, String path){
        this.exception = ex.getClass().getSimpleName();
        this.constraintsViolations = getErrorList(ex.getConstraintViolations());
        this.path = path;
    }

    /*
    @Override
    public String toString() {
        return "ErrorMessage{" +
                "exception='" + exception + '\'' +
                ", message={" + getErrorList(this.constraintsViolations) + '\'' +
                "}, path='" + path + '\'' +
                '}';
    }
*/
    private List<String> getErrorList(Set<ConstraintViolation<?>> cvs){
        List<String> result = new ArrayList<>();

        for (ConstraintViolation constraintViolation : cvs){
            String value = String.format(
                "{\n" +
                    "entity:" + constraintViolation.getRootBean() + ",\n" +
                    "property:" + constraintViolation.getPropertyPath() + ",\n" +
                    "invalidValue:" + constraintViolation.getInvalidValue() + ",\n" +
                    "message:" + constraintViolation.getMessage() +
                "\n}");

            result.add(value);
        }
        return result;
    }

    /*
    excepcion ex.cause.cause
    String.format("{
        entity: excepcion.constraintViolations[0].rootBean,
        property: excepcion.constraintViolations[0].propertyPath,
        rejectedValue: excepcion.constraintViolations[0].value,
        message: excepcion.constraintViolations[0].interpolatedMessage
    }")



     */
}
