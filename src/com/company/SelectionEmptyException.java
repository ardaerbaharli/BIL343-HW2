package com.company;

import javax.xml.bind.ValidationException;

public class SelectionEmptyException extends ValidationException {
    public SelectionEmptyException()
    {
        super("Selection cannot be empty!");
    }
}
