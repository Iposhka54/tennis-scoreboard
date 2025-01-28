package com.iposhka.util;

import com.iposhka.exception.InvalidDataException;
import lombok.experimental.UtilityClass;


@UtilityClass
public class Validator {
    private static final String NAME_CANNOT_BE_EMPTY = "Name can not be empty";
    private static final String NAME_CANNOT_BE_THE_SAME = "Name can not be the same";
    private static final String NAME_MUST_CONSIST_OF_LETTERS = "Name must consist of letters and 1 space";

    public static void checkNames(String name1, String name2) {
        validateNames(name1, name2);

        if (name1.equals(name2)) {
            throw new InvalidDataException(NAME_CANNOT_BE_THE_SAME);
        }

    }

    private static void validateNames(String... names) {
        String regex = "^[A-Za-z]+ [A-Za-z]+$"; // Name can contain only one space & only english letters

        if (areNamesEmpty(names)) {
            throw new InvalidDataException(NAME_CANNOT_BE_EMPTY);
        }

        for (String name : names) {
            if (!name.matches(regex)) {
                throw new InvalidDataException(NAME_MUST_CONSIST_OF_LETTERS);
            }

        }
    }


    private boolean areNamesEmpty(String... names) {
        for (String name : names) {
            if (name == null || name.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
