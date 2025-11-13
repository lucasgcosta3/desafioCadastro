package model;

public enum PetSex {
    MALE("Macho"),
    FEMALE("Femea");

    private final String description;

    PetSex(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
