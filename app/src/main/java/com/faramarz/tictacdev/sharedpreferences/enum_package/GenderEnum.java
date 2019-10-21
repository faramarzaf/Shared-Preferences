package com.faramarz.tictacdev.sharedpreferences.enum_package;

public enum GenderEnum {

    MALE("Male", 0),
    FEMALE("Female", 1);

     private String stringGender;
     private int intValue;

    GenderEnum(String text, int value) {
        this.stringGender = text;
        this.intValue = value;
    }


    public String getStringGender() {
        return stringGender;
    }

    public int getIntValue() {
        return intValue;
    }


}
