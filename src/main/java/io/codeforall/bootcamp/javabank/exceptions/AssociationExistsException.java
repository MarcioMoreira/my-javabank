package io.codeforall.bootcamp.javabank.exceptions;

import io.codeforall.bootcamp.javabank.errors.ErrorMessage;

public class AssociationExistsException extends JavaBankException{

    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}
