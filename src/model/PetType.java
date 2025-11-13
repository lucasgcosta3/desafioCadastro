package model;

public enum PetType {
    DOG("Cachorro"),
    CAT("Gato");

    private final String description;

    PetType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
