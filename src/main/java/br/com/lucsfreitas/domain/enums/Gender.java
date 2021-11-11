package br.com.lucsfreitas.domain.enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    NON_BINARY("NON BINARY");

    private String description;

    Gender(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
