package com.myfinalblogapi.exception;

import ch.qos.logback.core.boolex.EvaluationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFounException extends RuntimeException {

    private String ResouceName;
    private String fieldName;
    private Long fieldValue;

    public ResourceNotFounException(String resouceName, String fieldName, Long fieldValue) {
        super(String.format("%s not found with %s : %s", resouceName, fieldName, fieldValue));
        ResouceName = resouceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResouceName() {

        return ResouceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Long getFieldValue() {

        return fieldValue;
    }
}
