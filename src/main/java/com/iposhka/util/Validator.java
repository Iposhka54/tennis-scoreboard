package com.iposhka.util;

import com.iposhka.exception.InvalidDataException;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class Validator {
    private static final String NAME_CANNOT_BE_EMPTY = "Name can not be empty";
    private static final String NAME_CANNOT_BE_THE_SAME = "Name can not be the same";
    private static final String NAME_MUST_CONSIST_OF_LETTERS = "Name must consist of letters and 1 space";
    private static final Integer COUNT_SPACE_IN_NAME = 1;

    public static void checkNames(String name1, String name2){
        ifStrsNullOrEmptyThrowException(name1, name2);

        if(name1.equals(name2)){
            throw new InvalidDataException(NAME_CANNOT_BE_THE_SAME);
        }
        name1 = name1.trim();
        name2 = name2.trim();

        validateName(name1);
        validateName(name2);
    }

    private static void validateName(String name){
        int spaceCount = 0;
        for (char c : name.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                spaceCount++;
            } else if (!Character.isLetter(c)) {
                throw new InvalidDataException(NAME_MUST_CONSIST_OF_LETTERS);
            }
        }

        if (spaceCount != COUNT_SPACE_IN_NAME) {
            throw new InvalidDataException(NAME_MUST_CONSIST_OF_LETTERS);
        }
    }

    private void ifStrsNullOrEmptyThrowException(String... strs){
        for (String str : strs) {
            if(str == null || str.isBlank()){
                throw new InvalidDataException(Validator.NAME_CANNOT_BE_EMPTY);
            }
        }
    }
}
